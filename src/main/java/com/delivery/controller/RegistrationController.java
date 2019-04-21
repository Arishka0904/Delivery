package com.delivery.controller;

import com.delivery.domain.User;
import com.delivery.domain.dto.CaptchaResponseDto;
import com.delivery.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private UserServiceImplementation userServiceImplementation;
    private RestTemplate restTemplate;

    public RegistrationController(UserServiceImplementation userServiceImplementation, RestTemplate restTemplate) {
        this.userServiceImplementation = userServiceImplementation;
        this.restTemplate = restTemplate;
    }
    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {

        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation can not be empty!");
            return "registration";
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Password are different!");
            return "registration";
        }
        if (bindingResult.hasErrors() || !response.isSuccess()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (userServiceImplementation.isUserExist(user)) {
            model.addAttribute("usernameError", "User already exists!");
            return "registration";
        }
        if (userServiceImplementation.isEmailExist(user)) {
            model.addAttribute("emailError", "Email already exists!");
            return "registration";
        }
        userServiceImplementation.addNewUser(user);

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userServiceImplementation.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}

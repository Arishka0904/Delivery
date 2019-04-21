package com.delivery.controller;


import com.delivery.domain.Role;
import com.delivery.domain.User;
import com.delivery.service.UserServiceImplementation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserAdministrationController {

    private UserServiceImplementation userServiceImplementation;

    public UserAdministrationController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userServiceImplementation.findAll());

        return "userList";
    }

    @GetMapping("/user/{user}")
    public String getUserData(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }


    @PostMapping("/user")
    public String updateUserRole(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userServiceImplementation.updateUserRole(user, username, form);

        return "redirect:/user";
    }
}


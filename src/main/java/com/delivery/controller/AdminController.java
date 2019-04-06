package com.delivery.controller;


import com.delivery.domain.Category;
import com.delivery.domain.Product;
import com.delivery.domain.Role;
import com.delivery.domain.User;
import com.delivery.domain.dto.ProductDto;
import com.delivery.service.ProductServiceImplementation;
import com.delivery.service.UserServiceImplementation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private UserServiceImplementation userServiceImplementation;
    private ProductServiceImplementation productServiceImplementation;

    public AdminController(UserServiceImplementation userServiceImplementation, ProductServiceImplementation productServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
        this.productServiceImplementation = productServiceImplementation;
    }

    @GetMapping("/user")
    public String userList(Model model) {
        model.addAttribute("users", userServiceImplementation.findAll());

        return "userList";
    }


    @GetMapping("/user/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
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

    @GetMapping("/product")
    public String getProductList(Model model) {
        model.addAttribute("products", productServiceImplementation.findAllCurrentProduct());

        return "productList";
    }

    @GetMapping("/product/{product}")
    public String getProductEdit(@PathVariable Product product, Model model) {

        model.addAttribute("product", product);
        model.addAttribute("categories", Category.values());

        return "productEdit";
    }

    @PostMapping("/product")
    public String saveProductEdit(@ModelAttribute ProductDto product) {

        productServiceImplementation.updateProductInDB(product.buildProduct());

        return "redirect:/product";
    }

    @GetMapping("/product/add")
    public String productAddForm() {


        return "productAdd";
    }

    @PostMapping("/product/add")
    public String addNewProduct(@ModelAttribute @Valid ProductDto product,
                                BindingResult bindingResult, Model model) {

        if (productServiceImplementation.isProductExist(product.buildProduct())) {
            model.addAttribute("productNameError", "Product already exists!");
            return "productAdd";
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "productAdd";
        }

        productServiceImplementation.addNewProductInDB(product.buildProduct());

        return "redirect:/product";
    }
}
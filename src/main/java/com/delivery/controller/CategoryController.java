package com.delivery.controller;

import com.delivery.domain.Category;
import com.delivery.service.ProductServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    private ProductServiceImplementation productService;

    public CategoryController(ProductServiceImplementation productService) {
        this.productService = productService;
    }


    @GetMapping("/category/{type}")
    public String showOne(@PathVariable("type") Category category,
                          Model model) {

        model.addAttribute("category", category);
        model.addAttribute("products",productService.findCurrentProductByCategory(category) );

        return "showProducts";
    }


}

package com.delivery.controller;


import com.delivery.service.ProductServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    private ProductServiceImplementation productServiceImplementation;

    public OrderController(ProductServiceImplementation productServiceImplementation) {
        this.productServiceImplementation = productServiceImplementation;
    }


    @GetMapping("/")
    public String getProductList() {
        return "mainPage";
    }
public class OrderController {
}

package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/")
    public String home(){
        return "redirect:/main";
    }
    @GetMapping
    public String list(){
        return "main";
    }

}
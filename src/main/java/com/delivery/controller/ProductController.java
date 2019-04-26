package com.delivery.controller;

import com.delivery.domain.Product;
import com.delivery.enums.ProductStatusEnum;
import com.delivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
//@PreAuthorize("hasAuthority('USER')")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/show/{productId}")
    public String showOne(@PathVariable("productId") Long productId, Model model) {

        Product product = productService.findById(productId);

        // Product is not available
        if (product.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            model.addAttribute("msg", "Product is unavailable!");
            model.addAttribute("url", "/");
            return  "common/error";
        }
        model.addAttribute(product);
        return "/product/show";
    }
}

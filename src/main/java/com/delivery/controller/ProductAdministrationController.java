package com.delivery.controller;

import com.delivery.domain.Product;
import com.delivery.domain.dto.ProductDto;
import com.delivery.service.ProductServiceImplementation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductAdministrationController {

    private ProductServiceImplementation productServiceImplementation;

    public ProductAdministrationController(ProductServiceImplementation productServiceImplementation) {
        this.productServiceImplementation = productServiceImplementation;
    }

    @GetMapping("/product")
    public String getAllCurrentProduct(Model model) {
        model.addAttribute("products", productServiceImplementation.findAllCurrentProduct());

        return "productList";
    }

    @GetMapping("/product/{product}")
    public String getProductData(@PathVariable Product product, Model model) {

        model.addAttribute("product", product);

        return "productEdit";
    }

    @PostMapping("/product")
    public String updateProductData(@ModelAttribute ProductDto product) {

        productServiceImplementation.updateProductInDB(product.buildProduct());

        return "redirect:/product";
    }

    @GetMapping("/product/add")
    public String addNewProduct() {
        return "productAdd";
    }

    @PostMapping("/product/add")
    public String addNewProduct(@ModelAttribute @Valid Product product,
                                BindingResult bindingResult, Model model) {

        if (productServiceImplementation.isProductExist(product)) {
            model.addAttribute("productNameError", "Product already exists!");
            return "productAdd";
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "productAdd";
        }

        productServiceImplementation.addNewProductInDB(product);

        return "redirect:/product";
    }
}

package com.delivery.controller;

import com.delivery.domain.User;
import com.delivery.domain.dto.ProductDto;
import com.delivery.enums.ResultEnum;
import com.delivery.exception.MyException;
import com.delivery.form.ItemForm;
import com.delivery.service.CartService;
import com.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collection;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    private UserService userService;

    @GetMapping("")
    public String findAll(Model model){
        Collection<ProductDto> items = cartService.findAll();
        BigDecimal total = cartService.getTotal();
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        return "/cart/index";
    }

    @PostMapping("")
    public String addToCart(@Valid ItemForm itemForm,
                            BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            throw new MyException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        cartService.addItem(itemForm);
        return "redirect:" + "/cart";
    }

    @PostMapping("/checkout")
    public  String checkout(@RequestParam("address") String address, Model model, Principal principal) {
        User user = userService.findByName(principal.getName());// Email as username
        cartService.checkout(user, address);

        model.addAttribute("msg", ResultEnum.CART_CHECKOUT_SUCCESS.getMessage());
        model.addAttribute("url", "/order");
        return "/common/success";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("product_id") Long productId) {
        cartService.removeItem(productId);
        return "redirect:" + "/cart";
    }

    @GetMapping("/change")
    public String plus(@RequestParam("product_id") Long productId, @RequestParam("quantity") Integer quantity) {
        cartService.updateQuantity(productId, quantity);
        return "redirect:" + "/cart";
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

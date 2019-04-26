package com.delivery.controller;


import com.delivery.domain.Order;
import com.delivery.domain.ProductInOrder;
import com.delivery.domain.Role;
import com.delivery.service.OrderService;
import com.delivery.service.ProductService;
import com.delivery.service.ProductServiceImplementation;
import com.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService;

    private UserService userService;

    @GetMapping("/order")
    public String orderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                            Authentication authentication,
                            Model model) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Order> orderList;
        if(authentication.getAuthorities().contains(Role.USER)) {
            orderList = orderService.findByBuyerID(userService.findByName(authentication.getName()).getId(), request);
        } else {
            orderList = orderService.findAll(request);
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("statusArray", new String[] {"New","Finished" ,"Canceled" });
        model.addAttribute("orders", orderList);
        return "/order/index";
    }

    @GetMapping("/order/cancel/{id}")
    public String cancel(@PathVariable("id") Long orderId, Model model, Authentication authentication){
        Order order = orderService.findOne(orderId);
        if(!authentication.getName().equals(order.getUser().getUsername())
                && authentication.getAuthorities().contains(Role.USER) ){
            return "redirect:" + "/403";
        }
        orderService.cancel(orderId);
        return "redirect:" + "/order";
    }

    @GetMapping("/order/finish/{id}")
    public String finish(@PathVariable("id") Long orderId, Model model){
        orderService.finish(orderId);
        return "redirect:" + "/order";
    }

    @GetMapping("/order/show/{id}")
    public String show(@PathVariable("id") Long orderId, Model model, Authentication authentication){
        boolean isCustomer = authentication.getAuthorities().contains(Role.USER);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Order order = orderService.findOne(orderId);
        if( isCustomer && !userDetails.getUsername().equals(order.getUser().getUsername())) {
            return "redirect:" + "/403";
        }

        Collection<ProductInOrder> items = order.getProductsInOrder();
        model.addAttribute("items", items);
        return "/order/show";
    }


    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

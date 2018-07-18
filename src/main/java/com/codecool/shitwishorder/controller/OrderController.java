package com.codecool.shitwishorder.controller;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.service.ShitWishOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    ShitWishOrderService shitWishOrderService;

    @GetMapping(value = "/orders/{id}")
    public String getOrder(@PathVariable("id") Long id) {
        ShitWishOrder order = shitWishOrderService.findById(id);
        return "majd json";
    }

    @PostMapping(value = "/orders")
    public String postOrder(ShitWishOrder shitWishOrder) {
        shitWishOrderService.saveOrder(shitWishOrder);
        return "oksa?";
    }
}

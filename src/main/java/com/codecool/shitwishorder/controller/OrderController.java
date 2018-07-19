package com.codecool.shitwishorder.controller;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.service.ShitWishOrderService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    ShitWishOrderService shitWishOrderService;

    @Autowired
    public OrderController(ShitWishOrderService shitWishOrderService) {
        this.shitWishOrderService = shitWishOrderService;
    }

    @GetMapping(value = "/orders/{id}")
    public String getOrder(@PathVariable("id") Long id, @RequestHeader Map<String, String> header) {
        if (shitWishOrderService.findById(id, header.get("Authorization")) != null) {
            ShitWishOrder order = shitWishOrderService.findById(id, header.get("Authorization"));
            String ret = shitWishOrderService.jsonStringBuilder(order);
            return ret;
        } else {
            JSONObject error = new JSONObject();
            error.put("error", "no such order, or invalid token");
            return error.toString();
        }
    }


    @PostMapping(value = "/orders")
    public String postOrder(@RequestParam("zipcode") String zipcode, @RequestParam("country") String country,
                            @RequestParam("city") String city, @RequestParam("street") String street,
                            @RequestParam("products") String productString,  @RequestHeader Map<String, String> header) {
        Map<Integer, Integer> products = new HashMap<Integer, Integer>();
        JSONArray productJSON = new JSONArray(productString);
        for (Object object: productJSON) {
            JSONObject jsonObject = (JSONObject) object;
            products.put(jsonObject.getInt("id"), jsonObject.getInt("amount"));
        }
        ShitWishOrder order = shitWishOrderService.saveOrder(new ShitWishOrder(zipcode, country, city, street, products), header.get(("Authorization")));
        if (order!=null){
            return shitWishOrderService.jsonStringBuilder(order);
        } else {
            JSONObject error = new JSONObject();
            error.put("error", "Token Expired or Invalid Token");
            return error.toString();
            }
        }


}

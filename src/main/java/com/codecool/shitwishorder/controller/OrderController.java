package com.codecool.shitwishorder.controller;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.service.ShitWishOrderService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    ShitWishOrderService shitWishOrderService;

    @GetMapping(value = "/orders/{id}")
    public String getOrder(@PathVariable("id") Long id, @RequestHeader Map<String, String> header) {
        ShitWishOrder ret = shitWishOrderService.findById(id, header.get("Authorization"));
        if  (ret!=null){
            return ret.jsonStringBuilder();
        }
        return "{'error': 'no such order'}";
    }

    @PostMapping(value = "/orders")
    public String postOrder(@RequestParam("products") String productString,  @RequestHeader Map<String, String> header) {
        Map<Integer, Integer> products = new HashMap<Integer, Integer>();
        JSONArray productJSON = new JSONArray(productString);
        for (Object object: productJSON) {
            JSONObject jsonObject = (JSONObject) object;
            products.put(jsonObject.getInt("id"), jsonObject.getInt("amount"));
        }
        ShitWishOrder returnOrder = new ShitWishOrder(products);
        ShitWishOrder ret = shitWishOrderService.saveOrder(returnOrder, header.get(("Authorization")));
        if (ret==null){
        return "{'error':Token Expired or Invalid Token}";} else {
            return ret.jsonStringBuilder();
        }
    }
}

package com.codecool.shitwishorder.controller;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.service.ShitWishOrderService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    ShitWishOrderService shitWishOrderService;

    @GetMapping(value = "/orders/{id}")
    public String getOrder(@PathVariable("id") Long id) {
        ShitWishOrder order = shitWishOrderService.findById(id);
        JSONObject entity = new JSONObject();
        entity.put("id", order.getOrder_id());
        entity.put("user_id", order.getUser_id());
        List<JSONObject> products = new ArrayList<JSONObject>();
        for (Map.Entry<Integer, Integer> entry: order.getProducts().entrySet()) {
            JSONObject lineItem = new JSONObject();
            lineItem.put("id", entry.getKey());
            lineItem.put("amount", entry.getValue());
            products.add(lineItem);
        }
        entity.put("products", products);
        return entity.toString();
    }

    @PostMapping(value = "/orders")
    public String postOrder(@RequestParam("user_id") Long userID, @RequestParam("products") String productString) {
        Map<Integer, Integer> products = new HashMap<Integer, Integer>();
        JSONArray productJSON = new JSONArray(productString);
        for (Object object: productJSON) {
            JSONObject jsonObject = (JSONObject) object;
            products.put(jsonObject.getInt("id"), jsonObject.getInt("amount"));
        }
        shitWishOrderService.saveOrder(new ShitWishOrder(userID, products));
        return "oksa?";
    }
}

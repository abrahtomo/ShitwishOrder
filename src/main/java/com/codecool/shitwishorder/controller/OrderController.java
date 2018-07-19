package com.codecool.shitwishorder.controller;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.service.ShitWishOrderService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    ShitWishOrderService shitWishOrderService;

    @GetMapping(value = "/orders/{id}")
    public ResponseEntity getOrder(@PathVariable("id") Long id, @RequestHeader Map<String, String> header) {

        if (shitWishOrderService.findById(id, header.get("Authorization")) != null) {
                ShitWishOrder order = shitWishOrderService.findById(id, header.get("Authorization"));
//                String ret = shitWishOrderService.jsonStringBuilder(order);
                ResponseEntity<ShitWishOrder> response = new ResponseEntity(order, HttpStatus.OK);

                return response; }
        else {
            JSONObject error = new JSONObject();
            error.put("error", "no such order, or invalid token");
            ResponseEntity<String> response = new ResponseEntity(error.toString(), HttpStatus.BAD_REQUEST);
            return response;
        }
    }


    @PostMapping(value = "/orders")
    public ResponseEntity postOrder(@RequestParam("zipcode") String zipcode, @RequestParam("country") String country,
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
            ResponseEntity<ShitWishOrder> response = new ResponseEntity(order, HttpStatus.OK);
            return response; }
         else {
            JSONObject error = new JSONObject();
            error.put("error", "no such order, or invalid token");
            ResponseEntity<JSONObject> response = new ResponseEntity(error.toString(), HttpStatus.BAD_REQUEST);
            return response;
            }
        }


}

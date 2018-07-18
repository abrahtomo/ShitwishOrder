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
        JSONObject addressDetails = new JSONObject();
        entity.put("id", order.getOrder_id());
        addressDetails.put("zipcode", order.getZipcode());
        addressDetails.put("country", order.getCountry());
        addressDetails.put("city", order.getCity());
        addressDetails.put("street;", order.getStreet());
        entity.put("address", addressDetails);
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
    public String getOrder(@PathVariable("id") Long id, @RequestHeader Map<String, String> header) {
        ShitWishOrder ret = shitWishOrderService.findById(id, header.get("Authorization"));
        if  (ret!=null){
            return ret.jsonStringBuilder();
        }
        return "{'error': 'no such order'}";
    }

    @PostMapping(value = "/orders")
    public String postOrder(@RequestParam("zipcode") String zipcode, @RequestParam("country") String country,
                            @RequestParam("city") String city, @RequestParam("street") String street,
                            @RequestParam("products") String productString) {
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
        shitWishOrderService.saveOrder(new ShitWishOrder(zipcode, country, city, street, products));
        return "oksa?";
    }
}

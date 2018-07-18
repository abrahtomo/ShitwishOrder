package com.codecool.shitwishorder.service;

import com.codecool.shitwishorder.model.ShitWishOrder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InitializerBean {

    public InitializerBean(ShitWishOrderService shitWishOrder) {
        /*Map orderMap = new HashMap<Integer, Integer>();
        orderMap.put(3, 3);
        shitWishOrder.saveOrder(new ShitWishOrder(1, orderMap));*/
    }
}

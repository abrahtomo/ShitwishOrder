package com.codecool.shitwishorder.service;

import com.codecool.shitwishorder.model.ShitWishOrder;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(ShitWishOrderService shitWishOrder) {
        shitWishOrder.saveOrder(new ShitWishOrder());
    }
}

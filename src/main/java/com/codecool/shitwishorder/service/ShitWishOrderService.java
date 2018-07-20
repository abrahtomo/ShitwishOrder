package com.codecool.shitwishorder.service;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.repository.ShitWishOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShitWishOrderService {

    @Autowired
    private Authenticator authenticator;


    @Autowired
    private ShitWishOrderRepository shitWishOrderRepository;


    public ShitWishOrder saveOrder(ShitWishOrder order, String token){
        String authToken = stripToken(token);
        String user_id = authenticator.getTokenString(authToken);
        if (user_id.equals("Token Expired")|| user_id.equals("Invalid Token")){
            return null;
        } else {
        order.setUser_id(user_id);
        ShitWishOrder ret =  shitWishOrderRepository.save(order);
        return ret;}
    }


    public ShitWishOrder findById(long id, String token){
        String authToken = stripToken(token);
        String user_id = authenticator.getTokenString(authToken);


        if (user_id.equals("Token Expired") || user_id.equals("Invalid Token")){
            return null;
        } else if(shitWishOrderRepository.findOne(id)!=null&& shitWishOrderRepository.findOne(id).getUser_id().equals( user_id)){
            return shitWishOrderRepository.findOne(id);
        } else {
        return null;}


    }

    private String stripToken(String token){
        return token.split(" ")[1];
    }

}

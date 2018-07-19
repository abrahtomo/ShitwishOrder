package com.codecool.shitwishorder.service;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.repository.ShitWishOrderRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShitWishOrderService {

    @Autowired
    private Authenticator authenticator;


    @Autowired
    private ShitWishOrderRepository shitWishOrderRepository;

    public List<ShitWishOrder> findAll(){
        return shitWishOrderRepository.findAll();
    }

    public void deleteAll(){
        shitWishOrderRepository.deleteAll();
    }

    public ShitWishOrder saveOrder(ShitWishOrder order, String token){

        String authToken = authenticator.getTokenString(token);
        String user_id = stripToken(authToken);
        if (authToken.equals("Token Expired")|| authToken.equals("Invalid Token")){
            return null;
        } else {
        order.setUser_id(user_id);
        ShitWishOrder ret =  shitWishOrderRepository.save(order);
        return ret;}
    }


    public ShitWishOrder findById(long id, String token){
        String authToken = authenticator.getTokenString(token);
        String user_id = stripToken(authToken);


        if (authToken.equals("Token Expired") || authToken.equals("Invalid Token")){
            System.out.println("alma");
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

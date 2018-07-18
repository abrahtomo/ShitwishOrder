package com.codecool.shitwishorder.service;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.repository.ShitWishOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        String strippedToken = stripToken(token);
        String user_id = authenticator.getTokenString(strippedToken);
        if (user_id.equals("Token Expired")|| user_id.equals("Invalid Token")){
            return null;
        } else {
        order.setUser_id(user_id);
        ShitWishOrder ret =  shitWishOrderRepository.save(order);
        return ret;}
    }


    public ShitWishOrder findById(long id, String token){

        String strippedToken = stripToken(token);
        String user_id = authenticator.getTokenString(strippedToken);
        if (user_id.equals("Token Expired")|| user_id.equals("Invalid Token")){
            return null;
        } else if(shitWishOrderRepository.findOne(id).getUser_id().equals( user_id)){
            return shitWishOrderRepository.findOne(id);
        }
        return null;


    }

    private String stripToken(String token){
        return token.split(" ")[1];
    }

}

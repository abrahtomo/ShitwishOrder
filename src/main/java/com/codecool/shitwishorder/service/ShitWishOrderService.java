package com.codecool.shitwishorder.service;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.repository.ShitWishOrderRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ShitWishOrderService  {

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private ShitWishOrderRepository shitWishOrderRepository;


    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public ShitWishOrderRepository getShitWishOrderRepository() {
        return shitWishOrderRepository;
    }

    public void setShitWishOrderRepository(ShitWishOrderRepository shitWishOrderRepository) {
        this.shitWishOrderRepository = shitWishOrderRepository;
    }


    public List<ShitWishOrder> findAll(){
        return shitWishOrderRepository.findAll();
    }

    public void deleteAll(){
        shitWishOrderRepository.deleteAll();
    }


    public ShitWishOrder saveOrder(ShitWishOrder order, String token){

        String authToken = authenticator.getTokenString(token);
        String user_id = stripToken(authToken);
        if (user_id.equals("Token Expired")|| user_id.equals("Invalid Token")){
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


    public String jsonStringBuilder(ShitWishOrder order){
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
    }
}

package com.codecool.shitwishorder.service;

import com.codecool.shitwishorder.model.ShitWishOrder;
import com.codecool.shitwishorder.repository.ShitWishOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShitWishOrderService {

    @Autowired
    private ShitWishOrderRepository shitWishOrderRepository;

    public List<ShitWishOrder> findAll(){
        return shitWishOrderRepository.findAll();
    }

    public void deleteAll(){
        shitWishOrderRepository.deleteAll();
    }

    public void saveOrder(ShitWishOrder order){
        shitWishOrderRepository.save(order);
    }

    public ShitWishOrder findById(long id){
        return shitWishOrderRepository.findOne(id);
    }
}

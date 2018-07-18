package com.codecool.shitwishorder.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "SWORDER")
public class ShitWishOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;

    private long user_id;

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "Products")
    @MapKeyColumn(name="product_id")
    @Column(name="quantity")
    private Map<Integer, Integer> products;

    public ShitWishOrder() {
    }

    public ShitWishOrder(long user_id, Map<Integer, Integer> products) {
        this.user_id = user_id;
        this.products = products;
    }

    public long getOrder_id() {
        return order_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }
}

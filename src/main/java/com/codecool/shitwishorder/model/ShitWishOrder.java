package com.codecool.shitwishorder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "SWORDER")
public class ShitWishOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;

    private String zipcode;

    private String country;

    private String city;

    private String street;

    private String user_id;

    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "Products")
    @MapKeyColumn(name="product_id")
    @Column(name="quantity")
    private Map<Integer, Integer> products;

    public ShitWishOrder() {
    }

    public ShitWishOrder(String zipcode, String country, String city, String street, Map<Integer, Integer> products) {
        this.zipcode = zipcode;
        this.country = country;
        this.city = city;
        this.street = street;
        this.products = products;
        if (zipcode == null || country == null || city == null || street == null || products == null){
            throw new IllegalArgumentException("Invalid parameter");
        }
    }

    public ShitWishOrder(Map<Integer, Integer> products) {
        this.products = products;
    }

    public long getOrder_id() {
        return order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
        if (products == null){
            throw new IllegalArgumentException();
        }
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
        if (zipcode == null){
            throw new IllegalArgumentException();
        }
    }

    public void setCountry(String country) {
        this.country = country;
        if (country == null){
            throw new IllegalArgumentException();
        }
    }

    public void setCity(String city) {
        this.city = city;
        if (city == null){
            throw new IllegalArgumentException();
        }
    }

    public void setStreet(String street) {
        this.street = street;
        if (street == null){
            throw new IllegalArgumentException();
        }
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}

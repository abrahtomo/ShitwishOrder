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

    private String zipcode;

    private String country;

    private String city;

    private String street;

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
    }

    public long getOrder_id() {
        return order_id;
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
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

package com.codecool.shitwishorder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Map;

@Entity
public class ShitWishOrder {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;
    @JsonProperty("user_id")
    private String user_id;
    @JsonProperty("products")
    @ElementCollection(targetClass = Integer.class)
    @CollectionTable(name = "Products")
    @MapKeyColumn(name="key")
    @Column(name="value")
    private Map<Integer, Integer> map;
}

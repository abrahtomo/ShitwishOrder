package com.codecool.shitwishorder.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShitWishOrderTest {

    private ShitWishOrder shitWishOrderTest = new ShitWishOrder();

    @Test
    public void testIfConstructorThrowsException(){
        assertThrows(IllegalArgumentException.class, ()-> {ShitWishOrder test = new ShitWishOrder(null, null, null, null, null);});
    }

    @Test
    public void testIfProductSetterThrowsException(){
        assertThrows(IllegalArgumentException.class, ()-> {shitWishOrderTest.setProducts(null);});
    }

    @Test
    public void testIfSetZipcodeThrowsException(){
        assertThrows(IllegalArgumentException.class, ()-> {shitWishOrderTest.setZipcode(null);});
    }

    @Test
    public void testIfSetCountryThrowsException(){
        assertThrows(IllegalArgumentException.class, ()-> {shitWishOrderTest.setCountry(null);});
    }

    @Test
    public void testIfSetCityThrowsException(){
        assertThrows(IllegalArgumentException.class, ()-> {shitWishOrderTest.setCity(null);});
    }

    @Test
    public void testIfSetStreetThrowsException(){
        assertThrows(IllegalArgumentException.class, ()-> {shitWishOrderTest.setStreet(null);});
    }

}
package com.codecool.shitwishorder.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testZipCodeGetter(){
        Map orderMap = new HashMap<Integer, Integer>();
        orderMap.put(3, 3);
        ShitWishOrder shitWishOrderTestGet = new ShitWishOrder("1111", "hu", "bp", "váci", orderMap);
        assertEquals("1111", shitWishOrderTestGet.getZipcode());
    }

    @Test
    public void testCountryGetter(){
        Map orderMap = new HashMap<Integer, Integer>();
        orderMap.put(3, 3);
        ShitWishOrder shitWishOrderTestGet = new ShitWishOrder("1111", "hu", "bp", "váci", orderMap);
        assertEquals("hu", shitWishOrderTestGet.getCountry());
    }

    @Test
    public void testCityGetter(){
        Map orderMap = new HashMap<Integer, Integer>();
        orderMap.put(3, 3);
        ShitWishOrder shitWishOrderTestGet = new ShitWishOrder("1111", "hu", "bp", "váci", orderMap);
        assertEquals("bp", shitWishOrderTestGet.getCity());
    }

    @Test
    public void testStreetGetter(){
        Map orderMap = new HashMap<Integer, Integer>();
        orderMap.put(3, 3);
        ShitWishOrder shitWishOrderTestGet = new ShitWishOrder("1111", "hu", "bp", "váci", orderMap);
        assertEquals("váci", shitWishOrderTestGet.getStreet());
    }

    @Test
    public void testProductsGetter(){
        Map orderMap = new HashMap<Integer, Integer>();
        orderMap.put(3, 3);
        ShitWishOrder shitWishOrderTestGet = new ShitWishOrder("1111", "hu", "bp", "váci", orderMap);
        assertEquals(orderMap, shitWishOrderTestGet.getProducts());
    }
}
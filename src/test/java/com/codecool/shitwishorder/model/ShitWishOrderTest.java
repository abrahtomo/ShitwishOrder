package com.codecool.shitwishorder.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;
import static org.junit.jupiter.api.Assertions.*;

class ShitWishOrderTest {

    Map orderMap = new HashMap<Integer, Integer>();
    {{put(3, 3);}}
    private ShitWishOrder shitWishOrderTest = new ShitWishOrder("1111", "hu", "bp", "váci", orderMap);

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
       assertEquals("1111", shitWishOrderTest.getZipcode());
    }

    @Test
    public void testCountryGetter(){
       assertEquals("hu", shitWishOrderTest.getCountry());
    }

    @Test
    public void testCityGetter(){
        assertEquals("bp", shitWishOrderTest.getCity());
    }

    @Test
    public void testStreetGetter(){
        assertEquals("váci", shitWishOrderTest.getStreet());
    }

    @Test
    public void testProductsGetter(){
        assertEquals(orderMap, shitWishOrderTest.getProducts());
    }

}
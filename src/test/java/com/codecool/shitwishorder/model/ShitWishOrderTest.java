package com.codecool.shitwishorder.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShitWishOrderTest {

    private ShitWishOrder shitWishOrderTest = new ShitWishOrder();

    @Test
    public void testIfThrowsException(){
        assertThrows(IllegalArgumentException.class, ()-> {ShitWishOrder test = new ShitWishOrder(null, null, null, null, null);});
    }

}
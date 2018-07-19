package com.codecool.shitwishorder.service;
import com.codecool.shitwishorder.model.ShitWishOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ShitWishOrderServiceTest {


    @Autowired
    ShitWishOrderService service;

    @Test
    public void findById() {
        Authenticator authMock = mock(Authenticator.class);
        when(authMock.getTokenString("token token")).thenReturn("alma alma");
        service.setAuthenticator(authMock);

        ShitWishOrder testObj = new ShitWishOrder();
        Map<Integer, Integer> products = new HashMap<>();
        products.put(1, 1);

        testObj.setProducts(products);
        testObj.setCountry("Hun");
        testObj.setZipcode("1221");
        testObj.setCity("Bud");
        testObj.setStreet("virag");

        ShitWishOrder saved = service.saveOrder(testObj, "token token");

        ShitWishOrder searched = service.findById(saved.getOrder_id(), "token token");

        Assert.assertEquals("alma", searched.getUser_id());
    }

    @Test
    public void jsonStringBuilder() {
    }
}
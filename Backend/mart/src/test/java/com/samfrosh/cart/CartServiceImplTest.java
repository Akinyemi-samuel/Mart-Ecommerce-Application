package com.samfrosh.cart;

import com.samfrosh.dto.DtoCart;
import com.samfrosh.exception.CartError;
import com.samfrosh.service.CartServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class CartServiceImplTest {

    @Autowired
    CartServiceImpl cartService;

    @Test
    void getcartlist() throws CartError {
       List<DtoCart> cartItemsList =  cartService.getCartItems("25");
       cartItemsList.stream().collect(Collectors.toList()).forEach(System.out::println);

    }


}
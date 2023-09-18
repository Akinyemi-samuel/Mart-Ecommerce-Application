package com.samfrosh.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductStatusControllerTest {

    @Autowired
    private ProductStatusController productStatusController;

    @Autowired
    private ProductController productController;

    @Test
    public void findProductStatus(){
        System.out.println("hello: " + productStatusController.findProductStatus());
    }



}
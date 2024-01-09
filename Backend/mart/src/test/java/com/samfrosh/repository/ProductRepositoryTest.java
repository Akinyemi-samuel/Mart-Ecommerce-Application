package com.samfrosh.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

//    @Test
//    public void testProductRepository(){
//        System.out.println(productRepository.findByProductStatusId(1L, PageRequest.of(0,4)));
//    }
//
//    @Test
//    public void testProductRepositorysearch(){
//        System.out.println(productRepository.findByNameContaining("a", PageRequest.of(0,4)));
//    }

}
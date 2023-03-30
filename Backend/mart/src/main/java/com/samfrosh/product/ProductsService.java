package com.samfrosh.product;

import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductsService {
    Page<Product> getProductByStatusId(Long id , int page, int size) throws ProductNotFound;

    Product getProductById(Long id) throws ProductNotFound;

    Page<Product> searchProductByName(String name, int page, int size);

    List<Product> getRandomProductByCategoryIdLimit(Long id);
}

package com.samfrosh.service;

import com.samfrosh.model.ProductCategory;
import com.samfrosh.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl {

    private final ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryRepository.findAll();
    }


}

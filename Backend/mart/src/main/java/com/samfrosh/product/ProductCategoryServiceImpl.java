package com.samfrosh.product;

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

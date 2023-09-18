package com.samfrosh.controller;

import com.samfrosh.model.ProductCategory;
import com.samfrosh.service.ProductCategoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/productcategory")
@AllArgsConstructor
public class ProductCategoryController {

    private ProductCategoryServiceImpl productCategoryService;

    @GetMapping
    public List<ProductCategory> getProductCategoryList(){
        return productCategoryService.getProductCategoryList();
    }
}

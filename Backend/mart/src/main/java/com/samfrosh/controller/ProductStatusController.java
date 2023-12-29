package com.samfrosh.controller;

import com.samfrosh.model.ProductStatus;
import com.samfrosh.service.ProductStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin()
@RestController
@Tag(name = "Product Status")
public class ProductStatusController {

    @Autowired
    private ProductStatusService productStatusService;

    @GetMapping("/productstatus")
    public List<ProductStatus> getProductStatusList(){
        return productStatusService.getProductStatusList();
    }

}

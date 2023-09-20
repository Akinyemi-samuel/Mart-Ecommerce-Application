package com.samfrosh.service;

import com.samfrosh.model.ProductStatus;
import com.samfrosh.repository.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStatusServiceImpl implements ProductStatusService {

    @Autowired
    private ProductStatusRepository productStatusRepository;


    @Override
    public List<ProductStatus> getProductStatusList() {
        return productStatusRepository.findAll();
    }
}

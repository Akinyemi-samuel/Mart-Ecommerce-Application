package com.samfrosh.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductServiceStatus, ProductsService {

    @Autowired
    private ProductStatusRepository productStatusRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductStatus> getProductStatusList() {
        return productStatusRepository.findAll();
    }

    @Override
    public Page<Product> getProductByStatusId(Long id, int page, int size) throws ProductNotFound {
            return productRepository.findByProductStatusId(id, PageRequest.of(page, size));
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFound {
        return productRepository.findById(id).get();
    }

    @Override
    public Page<Product> searchProductByName(String name, int page, int size) {
        return productRepository.findByNameContaining(name, PageRequest.of(page, size));
    }

    @Override
    public List<Product> getRandomProductByCategoryIdLimit(Long id) {
        return productRepository.findByCategoryIds(id);
    }
}

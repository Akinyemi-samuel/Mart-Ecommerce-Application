package com.samfrosh.service;

import com.samfrosh.exception.ProductNotFound;
import com.samfrosh.model.Product;
import com.samfrosh.model.ProductStatus;
import com.samfrosh.repository.ProductRepository;
import com.samfrosh.repository.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProductByStatusId(Long id, int page, int size) throws ProductNotFound {
            return productRepository.findByProductStatusId(id, PageRequest.of(page, size));
    }

    @Override
    public List<Product> getProductByStatusIdLimit(Long id) {
        return productRepository.findByProductStatusIdLimit(id);
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFound {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFound("Product not found"));
    }

    @Override
    public Page<Product> searchProductByName(String name, int page, int size) {
        return productRepository.findByNameContaining(name, PageRequest.of(page, size));
    }

    @Override
    public List<Product> getRandomProductByCategoryIdLimit(Long id) {
        return productRepository.findByCategoryIdLimit(id);
    }
}

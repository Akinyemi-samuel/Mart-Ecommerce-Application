package com.samfrosh.repository;

import com.samfrosh.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "product-category", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
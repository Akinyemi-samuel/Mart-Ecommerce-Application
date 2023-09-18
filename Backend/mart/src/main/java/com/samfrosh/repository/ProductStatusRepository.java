package com.samfrosh.repository;

import com.samfrosh.model.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "product_status", path = "product_status")
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
}
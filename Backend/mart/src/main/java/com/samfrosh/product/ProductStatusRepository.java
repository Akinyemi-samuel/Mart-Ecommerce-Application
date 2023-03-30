package com.samfrosh.product;

import com.samfrosh.product.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "product_status", path = "product_status")
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
}
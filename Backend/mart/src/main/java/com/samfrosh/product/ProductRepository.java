package com.samfrosh.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByProductStatusId(Long id, Pageable pageable);

    Page<Product> findByNameContaining(String name, Pageable pageable);

    @Query(
             "SELECT p FROM Product p WHERE p.category.id = ?1 ORDER BY RAND() LIMIT 4"
    )
    List<Product>  findByCategoryIds(Long id);



}
package com.samfrosh.repository;

import com.samfrosh.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    @Query(
            value = "SELECT w FROM WishList w WHERE w.userId = :userId"
    )
    List<WishList> findAllWishListById(Long userId);
}
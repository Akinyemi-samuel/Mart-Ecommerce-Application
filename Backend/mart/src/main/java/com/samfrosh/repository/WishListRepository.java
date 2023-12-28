package com.samfrosh.repository;

import com.samfrosh.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM wishlist where user_id = :userId"
    )
    WishList getWishListByUserId(Long userId);
}
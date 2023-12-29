package com.samfrosh.repository;

import com.samfrosh.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM wishlist WHERE user_id = :userId"
    )
    Optional<List<WishList>> findAllWishListById(Long userId);
}
package com.samfrosh.repository;


import com.samfrosh.model.Cart;
import com.samfrosh.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    Optional<CartItems> findByCartAndProductId(Cart cart, String productId);

    Optional<List<CartItems>> findByCart(Cart cart);

    void deleteByIdAndProductId(Long id, String productId);
}
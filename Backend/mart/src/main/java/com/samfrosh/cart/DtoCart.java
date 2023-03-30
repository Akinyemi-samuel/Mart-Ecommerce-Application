package com.samfrosh.cart;

import jakarta.persistence.Column;

public record DtoCart(
        Long id,

        String productName,

        String productId,

        String productImg,

        int productQuantity,

        int productPrice
) {
}

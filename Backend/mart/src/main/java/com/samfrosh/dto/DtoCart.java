package com.samfrosh.dto;

public record DtoCart(
        Long id,

        String productName,

        String productId,

        String productImg,

        int productQuantity,

        int productPrice
) {
}

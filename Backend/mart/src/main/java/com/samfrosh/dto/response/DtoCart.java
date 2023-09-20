package com.samfrosh.dto.response;

public record DtoCart(
        Long id,

        String productName,

        String productId,

        String productImg,

        int productQuantity,

        int productPrice
) {
}

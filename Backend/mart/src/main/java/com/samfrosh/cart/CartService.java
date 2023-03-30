package com.samfrosh.cart;

import com.samfrosh.user.UserExits;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CartService {

    public String addItemsToCart(CartDto cartDto) throws CartError;

    List<DtoCart> getCartItems(String userId) throws CartError;

    void deleteProduct(String productId, Long userId);
}

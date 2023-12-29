package com.samfrosh.controller;

import com.samfrosh.dto.request.CartDto;
import com.samfrosh.dto.response.DtoCart;
import com.samfrosh.exception.CartError;
import com.samfrosh.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
@CrossOrigin()
@Tag(name = "Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PutMapping("/addproduct")
    public String addToCart(@RequestBody CartDto cartDto) throws CartError {
        return cartService.addItemsToCart(cartDto);
    }

    @GetMapping("/cartitems/{user_id}")
    public List<DtoCart> getCartItems(@PathVariable(value = "user_id") String userId) throws CartError {
        return cartService.getCartItems(userId);
    }

    @DeleteMapping("{product_id}/{item_id}")
    public void deleteProduct(
            @PathVariable(value = "product_id") String product_id,
            @PathVariable(value = "item_id") Long item_id) throws CartError {
        cartService.deleteProduct(product_id, item_id);
    }


}

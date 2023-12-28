package com.samfrosh.controller;

import com.samfrosh.dto.request.WishListDto;
import com.samfrosh.exception.ProductNotFound;
import com.samfrosh.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin()
public class WishListController {

    private final WishlistService wishlistService;

    public WishListController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String addProductToWishList(@RequestBody WishListDto wishListDto) throws ProductNotFound {
        wishlistService.AddproductToWishListByUser(wishListDto);
        return "Product Added to Wishlist successfully";
    }
}

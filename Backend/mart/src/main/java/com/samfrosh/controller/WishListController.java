package com.samfrosh.controller;

import com.samfrosh.dto.request.WishListDto;
import com.samfrosh.exception.ProductNotFound;
import com.samfrosh.model.WishList;
import com.samfrosh.service.WishlistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin()
@Tag(name = "WishList")
public class WishListController {

    private final WishlistService wishlistService;

    public WishListController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String addProductToWishList(@RequestBody WishListDto wishListDto) throws ProductNotFound {
        wishlistService.addProductToWishListByUser(wishListDto);
        return "Product Added to Wishlist successfully";
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("{userId}")
    public List<WishList> getAllProductByUserWishListId(@PathVariable(value = "userId") Long userId) throws ProductNotFound {
        return wishlistService.getAllProductByUserWishListId(userId);
    }
}

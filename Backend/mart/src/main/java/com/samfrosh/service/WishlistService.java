package com.samfrosh.service;

import com.samfrosh.dto.request.WishListDto;
import com.samfrosh.exception.ProductNotFound;
import com.samfrosh.model.Product;
import com.samfrosh.model.WishList;
import com.samfrosh.repository.WishListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class WishlistService {

    private final WishListRepository wishListRepository;
    private final ProductsService productsService;

    public List<WishList> getAllProductByUserWishListId(Long id) throws ProductNotFound {
        List<WishList> wishLists = wishListRepository.findAllWishListById(id);
        if (wishLists.isEmpty()) throw new ProductNotFound("User WishList Not Found");
        return wishLists;
    }

    public void addProductToWishListByUser(WishListDto wishListDto) throws ProductNotFound {

        try {
            Product product = productsService.getProductById(wishListDto.getProductId());
            log.info("Product received by userId -: " + wishListDto.getUserId());

            WishList wishListInstance = WishList.builder()
                    .userId(wishListDto.getUserId())
                    .product(product)
                    .build();

            wishListRepository.save(wishListInstance);
            log.info("Product added to wishlist successfully");

        } catch (ProductNotFound e) {
            throw new ProductNotFound(e);
        }

    }
}

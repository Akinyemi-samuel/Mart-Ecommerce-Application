package com.samfrosh.service;

import com.samfrosh.dto.request.WishListDto;
import com.samfrosh.exception.ProductNotFound;
import com.samfrosh.model.Product;
import com.samfrosh.model.WishList;
import com.samfrosh.repository.WishListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class WishlistService {

    private final WishListRepository wishListRepository;
    private final ProductsService productsService;

//    public WishList getAllProductByUserWishListId(){
//        wishListRepository.findAllById()
//    }

    public void AddproductToWishListByUser(WishListDto wishListDto) throws ProductNotFound {

        try{
            Product product = productsService.getProductById(wishListDto.getProductid());
            log.info("Product received by userId -: " + wishListDto.getUserid());

            WishList wishListInstance = WishList.builder()
                    .UserId(wishListDto.getUserid())
                    .Product(product)
                    .build();

            wishListRepository.save(wishListInstance);
            log.info("Product added to wishlist successfully");

        } catch (ProductNotFound e) {
            throw new ProductNotFound(e);
        }

    }
}

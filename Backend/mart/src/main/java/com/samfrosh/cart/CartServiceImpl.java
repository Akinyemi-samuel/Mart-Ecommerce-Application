package com.samfrosh.cart;

import com.samfrosh.user.UserExits;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Override
    public String addItemsToCart(CartDto cartDto) throws CartError {
        //!checks if that user has a cart account and if he has then check if the product he is trying to put in the cart already exists
        Optional<Cart> optional = cartRepository.findByUserId(cartDto.getUserid());
        Cart cart =null;
        CartItems cartItems = null;
        if (optional.isPresent()) {
            cart = optional.get();
            Optional<CartItems> optional2 = cartItemsRepository.findByCartAndProductId(cart,cartDto.getProductid());

            CartItems cartItems1 = null;
            if (optional2.isPresent()) {
                //!checks if that user has added this product previously and increment the item if the user Has
                cartItems = optional2.get();
                cartItems.setProductQuantity(cartItems.getProductQuantity() + 1);
                cartItems.setProductPrice(cartItems.getProductPrice() * 2);
                cartItemsRepository.save(cartItems);
                 throw new CartError("Cart has been modified successfully");
            }else{
                //!checks if that user has has added this product previously and if not add the product using the user id
                cartItems = CartItems
                        .builder()
                        .productImg(cartDto.getProductImg())
                        .productId(cartDto.getProductid())
                        .productPrice(cartDto.getProductPrice())
                        .productName(cartDto.getProductName())
                        .productQuantity(cartDto.getProductQuantity())
                        .cart(cart)
                        .build();
                cartItemsRepository.save(cartItems);
                throw new CartError("Item has been added successfully to cart");
            }

        }else{

            cart = Cart.
                    builder().
                    userId(cartDto.getUserid())
                    .productTotalPrice(cartDto.getProductPrice())
                    .productTotalQuantity(cartDto.getProductQuantity())
                    .build();

            cartItems = CartItems
                    .builder()
                    .productImg(cartDto.getProductImg())
                    .productId(cartDto.getProductid())
                    .productPrice(cartDto.getProductPrice())
                    .productName(cartDto.getProductName())
                    .productQuantity(cartDto.getProductQuantity())
                    .cart(cart)
                    .build();
            List<CartItems> cartItemsList = new ArrayList<>();
            cartItemsList.add(cartItems);
            cart.setCartItemsList(cartItemsList);
            cartRepository.save(cart);
            throw new CartError("Item has been added successfully to cart");
        }

    }

    @Override
    public List<DtoCart> getCartItems(String userId) throws CartError {
        Optional<Cart> optional = cartRepository.findByUserId(userId);
        Cart cart = optional.orElseThrow(()-> new CartError("User was not found"));

        Optional<List<CartItems>> optional1 = cartItemsRepository.findByCart(cart);
        List<CartItems> cartItems = null;
        if (optional1.isPresent()) {
            cartItems = optional1.get();
         List<DtoCart> dtoCart = cartItems.stream().map(str -> new DtoCart(
                    str.getId(),
                    str.getProductName(),
                    str.getProductId(),
                    str.getProductImg(),
                    str.getProductQuantity(),
                    str.getProductPrice())).collect(Collectors.toList());
         return dtoCart;
        }else{
            return null;
        }

    }

    @Transactional
    @Override
    public void deleteProduct(String productId, Long userId) {
        cartItemsRepository.deleteByIdAndProductId(userId,productId);
    }
}

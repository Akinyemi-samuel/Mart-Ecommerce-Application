package com.samfrosh.service;

import com.samfrosh.repository.CartItemsRepository;
import com.samfrosh.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {


    @InjectMocks
    private CartServiceImpl cartService;

    @MockBean
    private CartRepository cartRepositoryMock;

    @MockBean
    private CartItemsRepository cartItemsRepositoryMock;

    @BeforeEach
    void setUp() {
        cartService = new CartServiceImpl(cartRepositoryMock, cartItemsRepositoryMock);
    }

    @Test
     void test_successfully_delete_product() {
        //given
        String productId = "12";
        Long userId = 20L;

            //when
            cartService.deleteProduct(productId, userId);

            //then
            verify(cartItemsRepositoryMock, times(1)).deleteByIdAndProductId(userId, productId);

    }
}
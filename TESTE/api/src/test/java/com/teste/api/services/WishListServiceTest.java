package com.teste.api.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.teste.api.domain.WishProduct;
import com.teste.api.dto.wishproduct.WishProductRequestDTO;
import com.teste.api.exception.ApiException;
import com.teste.api.mocks.domain.WishProductFactory;
import com.teste.api.mocks.request.WishProductDTOFactory;
import com.teste.api.repository.WishProductRepository;
import com.teste.api.service.WishListService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class WishListServiceTest {
    @InjectMocks
    private WishListService wishListService;

    @Mock
    private WishProductRepository wishProductRepository;

    @BeforeEach
    public void setup() {
        when(wishProductRepository.save(any()))
                .thenAnswer(invocation -> {
                    WishProduct wishProduct = invocation.getArgument(0);
                    if(wishProduct.getUserId() == 2L) {
                        throw new RuntimeException();
                    }
                    return WishProductFactory.createValidWishProduct();
                });


        when(wishProductRepository.findAllByUserId(anyLong()))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    if(id == 2L) {
                        throw new RuntimeException();
                    }
                    return Arrays.asList(WishProductFactory.createValidWishProduct(),
                            WishProductFactory.createValidWishProductWithDifferentProduct());
                });

        when(wishProductRepository.findByUserIdAndProductCode(anyLong(), anyString()))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    String productCode = invocation.getArgument(1);
                    if(id == 2L && productCode.equals("123")) {
                        return Optional.empty();
                    }
                    return Optional.of(WishProductFactory.createValidWishProduct());
                });

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addProductToWishListSuccessfully() {
        WishProductRequestDTO wishProductRequestDTO =
                WishProductDTOFactory.createValidRequestWishProduct();

        String result = wishListService.addProductToWishList(wishProductRequestDTO);

        assertEquals("668ee91bbb862f3f391d6209", result);
    }

    @Test
    public void addProductToWishListThrowsException() {
        WishProductRequestDTO wishProductRequestDTO =
                WishProductDTOFactory.createInvalidRequestWishProduct();

        assertThrows(ApiException.class, () -> wishListService.addProductToWishList(wishProductRequestDTO));
    }

    @Test
    public void removeProductFromWishListSuccessfully() {
        doNothing().when(wishProductRepository).deleteById(anyString());

        assertDoesNotThrow(() -> wishListService.removeProductFromWishList(1L, "123"));
    }

    @Test
    public void removeProductFromWishListThrowsException() {
        doThrow(new RuntimeException()).when(wishProductRepository).deleteById(anyString());

        assertThrows(ApiException.class, () -> wishListService.removeProductFromWishList(2L, "123"));
    }

    @Test
    public void getWishProductsSuccessfully() {
        List<String> result = wishListService.getWishProducts(1L);

        assertEquals(2, result.size());
        assertTrue(result.contains("123"));
        assertTrue(result.contains("456"));
    }

    @Test
    public void getWishProductsThrowsException() {
        assertThrows(ApiException.class, () -> wishListService.getWishProducts(2L));
    }
}

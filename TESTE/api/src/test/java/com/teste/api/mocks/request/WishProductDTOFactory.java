package com.teste.api.mocks.request;

import com.teste.api.dto.wishproduct.WishProductRequestDTO;

public class WishProductDTOFactory {
    private WishProductDTOFactory() {

    }

    public static WishProductRequestDTO createValidRequestWishProduct() {
        return WishProductRequestDTO.builder()
                .userId(1L)
                .productCode("123").build();
    }

    public static WishProductRequestDTO createInvalidRequestWishProduct() {
        return WishProductRequestDTO.builder()
                .userId(2L)
                .productCode("123").build();
    }
}

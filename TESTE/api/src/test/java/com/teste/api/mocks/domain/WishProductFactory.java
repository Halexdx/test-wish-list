package com.teste.api.mocks.domain;

import com.teste.api.domain.WishProduct;

public class WishProductFactory {
    private WishProductFactory() {

    }

    public static WishProduct createValidWishProduct() {
        return WishProduct.builder()
                .id("668ee91bbb862f3f391d6209")
                .userId(1L)
                .productCode("123").build();
    }

    public static WishProduct createValidWishProductWithDifferentProduct() {
        return WishProduct.builder()
                .id("668ee91bbb862f3f391d6209")
                .userId(1L)
                .productCode("456").build();
    }
}

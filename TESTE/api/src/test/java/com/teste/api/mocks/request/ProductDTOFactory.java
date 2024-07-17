package com.teste.api.mocks.request;

import com.teste.api.dto.product.ProductItemDTO;
import com.teste.api.dto.product.ProductResponseDTO;
import java.util.List;

public class ProductDTOFactory {

    private ProductDTOFactory() {

    }

    public static ProductResponseDTO createValidResponseProduct() {
        return ProductResponseDTO.builder()
                .products(List.of(ProductDTOFactory.createProductItem()))
                .build();
    }

    private static ProductItemDTO createProductItem() {
        return ProductItemDTO.builder()
                .name("Tenis adidas")
                .stockAvailable(true)
                .build();
    }
}

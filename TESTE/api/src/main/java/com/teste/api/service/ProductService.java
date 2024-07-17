package com.teste.api.service;

import com.teste.api.client.rest.ProductClient;
import com.teste.api.dto.product.ProductResponseDTO;
import com.teste.api.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient productClient;

    public ProductResponseDTO getProduct() {
        try {
            return productClient.getProduct();
        } catch (Exception e) {
            log.error("Error getting product", e);
            throw new ApiException("Error getting product", 404);
        }
    }
}

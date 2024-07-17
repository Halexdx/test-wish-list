package com.teste.api.client.rest;

import com.teste.api.client.rest.configuration.FeignConfiguration;
import com.teste.api.dto.product.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ProductClient", url = "${product-url}", configuration = FeignConfiguration.class)
public interface ProductClient {

    @GetMapping(value="/", produces = "application/json", consumes = "application/json")
    ProductResponseDTO getProduct();
}

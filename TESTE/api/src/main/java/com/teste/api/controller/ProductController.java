package com.teste.api.controller;


import com.teste.api.dto.product.ProductResponseDTO;
import com.teste.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping("/list")
    public ProductResponseDTO getProducts() {
        log.info("Start to get products");
        return productService.getProduct();
    }
}

package com.teste.api.service;

import com.teste.api.domain.WishProduct;
import com.teste.api.dto.mappers.WishProductMapper;
import com.teste.api.dto.wishproduct.WishProductRequestDTO;
import com.teste.api.repository.WishProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.teste.api.exception.ApiException;

@Service
@Log4j2
@RequiredArgsConstructor
public class WishListService {
    private final WishProductRepository wishProductRepository;

    public String addProductToWishList(WishProductRequestDTO wishProductRequestDTO) {
        log.info("Adding product: {} to wish list of user: {} ",
                wishProductRequestDTO.productCode, wishProductRequestDTO.userId);
        try {
            WishProduct wishProduct =
                    WishProductMapper.INSTANCE.toWishProduct(wishProductRequestDTO);

            WishProduct saved = wishProductRepository.save(wishProduct);

            return saved.getId();
        } catch (Exception e) {
            log.error("Error adding product to wish list: {}", e.getMessage());
            throw new ApiException("Error adding product to wish list, duplicate data", 409);
        }
    }

    public void removeProductFromWishList(Long userId, String productCode) {
        log.info("Removing product wish list userId: {} and productCode: {}",
                userId, productCode);
        try {
            WishProduct productToDelete
                    = wishProductRepository.findByUserIdAndProductCode(userId, productCode)
                    .orElseThrow();
            wishProductRepository.deleteById(productToDelete.getId());
        } catch (Exception e) {
            log.error("Error removing product from wish list: {}", e.getMessage());
            throw new ApiException("Error removing product from wish list", 404);
        }
    }

    public List<String> getWishProducts(Long userId) {
        try {
            List<WishProduct> allByUserId = wishProductRepository.findAllByUserId(userId);
            return allByUserId.stream().map(WishProduct::getProductCode).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error getting wish list of user: {}", userId);
            throw new ApiException("Error getting wish list", 404);
        }
    }
}

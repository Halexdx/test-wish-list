package com.teste.api.repository;

import com.teste.api.domain.WishProduct;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishProductRepository extends MongoRepository<WishProduct, String> {
    List<WishProduct> findAllByUserId(Long userId);

    Optional<WishProduct> findByUserIdAndProductCode(Long userId, String productCode);
}


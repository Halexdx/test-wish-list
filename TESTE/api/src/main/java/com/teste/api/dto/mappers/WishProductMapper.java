package com.teste.api.dto.mappers;

import com.teste.api.domain.WishProduct;
import com.teste.api.dto.wishproduct.WishProductRequestDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface WishProductMapper {
    WishProductMapper INSTANCE = Mappers.getMapper(WishProductMapper.class);

    WishProduct toWishProduct(WishProductRequestDTO wishProductRequestDTO);
}

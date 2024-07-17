package com.teste.api.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseDTO {
    @JsonProperty("total")
    public int total;

    @JsonProperty("pageSize")
    public int pageSize;

    @JsonProperty("totalPages")
    public int totalPages;

    @JsonProperty("products")
    public List<ProductItemDTO> products;
}

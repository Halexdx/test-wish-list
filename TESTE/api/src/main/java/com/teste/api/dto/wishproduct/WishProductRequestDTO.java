package com.teste.api.dto.wishproduct;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishProductRequestDTO {
    @JsonProperty("user_id")
    public Long userId;

    @JsonProperty("product_code")
    public String productCode;
}

package com.teste.api.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    @JsonProperty("code")
    public String code;

    @JsonProperty("available")
    public boolean available;

    @JsonProperty("visible")
    public boolean visible;

    @JsonProperty("details")
    public ProductDetailsDTO details;

    @JsonProperty("image")
    public String image;
}


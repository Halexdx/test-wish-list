package com.teste.api.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailsDTO {
    @JsonProperty("name")
    public String name;

    @JsonProperty("description")
    public String description;

    @JsonProperty("available")
    public boolean available;

    @JsonProperty("visible")
    public boolean visible;

    @JsonProperty("size")
    public SizeDTO size;

    @JsonProperty("sku")
    public String sku;
}

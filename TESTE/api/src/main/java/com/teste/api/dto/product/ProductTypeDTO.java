package com.teste.api.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypeDTO {
    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;
}

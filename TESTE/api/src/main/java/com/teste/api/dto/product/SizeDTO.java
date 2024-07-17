package com.teste.api.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SizeDTO {
    @JsonProperty("code")
    public String code;

    @JsonProperty("label")
    public String label;
}
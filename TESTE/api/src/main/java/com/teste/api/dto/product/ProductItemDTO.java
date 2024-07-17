package com.teste.api.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductItemDTO {
    @JsonProperty("selectedProduct")
    public String selectedProduct;

    @JsonProperty("department")
    public DepartmentDTO department;

    @JsonProperty("brand")
    public BrandDTO brand;

    @JsonProperty("productType")
    public ProductTypeDTO productType;

    @JsonProperty("name")
    public String name;

    @JsonProperty("stockAvailable")
    public boolean stockAvailable;

    @JsonProperty("product")
    public ProductDTO productList;
}

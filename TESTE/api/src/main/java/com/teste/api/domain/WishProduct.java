package com.teste.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "userId_productCode_idx", def = "{'userId': 1, 'productCode': 1}", unique = true)
})
@Document(collection = "wish_product")
public class WishProduct {
    @Id
    @Indexed(unique=true)
    private String id;

    private Long userId;

    private String productCode;
}

package com.solution.recipetalk.dto.product;

import com.solution.recipetalk.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private String productName;
    private Long ingredientId;

    public static ProductResponseDTO toDTO(Product entity) {
        return ProductResponseDTO.builder()
                .productName(entity.getProductName())
                .ingredientId(entity.getIngredient().getId())
                .build();
    }
}

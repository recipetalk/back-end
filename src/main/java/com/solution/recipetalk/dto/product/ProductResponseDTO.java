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
    private Boolean isClosed;
    private Boolean isProductShutdown;

    public static ProductResponseDTO toDTO(Product entity) {
        return ProductResponseDTO.builder()
                .productName(entity.getProductName())
                .ingredientId(entity.getIngredient().getId())
                .isClosed(entity.getClosedDate() == null || "".equals(entity.getClosedDate()))
                .isProductShutdown(entity.getProductShutdownDate() == null || "".equals(entity.getProductName()))
                .build();
    }
}

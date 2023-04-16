package com.solution.recipetalk.domain.product.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "barcode")
    private Long barcode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @Column(name = "ingredient")
    private Ingredient ingredient;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @Column(name = "is_product_shutdown")
    private Boolean isProductShutdown;
}

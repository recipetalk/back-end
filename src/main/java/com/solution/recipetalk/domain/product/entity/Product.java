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
@Table(indexes = {@Index(name = "product_index", columnList = "barcode")})
public class Product extends AuditingEntity{
    @Id
    @Column(name = "barcode")
    private Long barcode;

    @Column(name = "product_registration_number", nullable = false)
    private Long productRegistrationNumber;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column
    private String company;

    @Column(name = "closed_date")
    private String closedDate;

    @Column(name = "is_product_shutdown")
    private String productShutdownDate;

}

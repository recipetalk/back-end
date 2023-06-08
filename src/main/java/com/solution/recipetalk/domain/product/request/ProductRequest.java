package com.solution.recipetalk.domain.product.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_request", indexes = {
        @Index(name = "idx_barcode", columnList = "barcode", unique = false)
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long barcode;

    @Column(name = "product_name")
    private String productName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state", nullable = false)
    private ProductRequestState state;
}

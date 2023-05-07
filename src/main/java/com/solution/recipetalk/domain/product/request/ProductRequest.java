package com.solution.recipetalk.domain.product.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_request")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @Id
    private String barcode;

    @Column(name = "product_name")
    private String productName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state", nullable = false)
    private ProductRequestState state;
}

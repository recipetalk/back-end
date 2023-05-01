package com.solution.recipetalk.dto.product.request;

import com.solution.recipetalk.domain.product.request.ProductRequest;
import com.solution.recipetalk.domain.product.request.ProductRequestState;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class ProductRequestRegisterDTO {
    @NonNull
    private Long barcode;
    @NonNull
    private String productName;

    public ProductRequest toEntity() {
        return ProductRequest.builder()
                .barcode(this.getBarcode())
                .productName(this.getProductName())
                .state(ProductRequestState.NEW)
                .build();
    }
}

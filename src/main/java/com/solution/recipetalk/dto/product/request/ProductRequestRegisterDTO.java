package com.solution.recipetalk.dto.product.request;

import com.solution.recipetalk.annotation.CustomLength;
import com.solution.recipetalk.domain.product.request.ProductRequest;
import com.solution.recipetalk.domain.product.request.ProductRequestState;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class ProductRequestRegisterDTO {
    @NonNull
    @CustomLength(equals = 13, message = "barcode")
    private Long barcode;
    @NonNull
    @CustomLength(max = 100, message = "productName")
    private String productName;

    public ProductRequest toEntity() {
        return ProductRequest.builder()
                .barcode(this.getBarcode())
                .productName(this.getProductName())
                .state(ProductRequestState.NEW)
                .build();
    }
}

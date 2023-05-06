package com.solution.recipetalk.service.product;

import org.springframework.http.ResponseEntity;

public interface FindProductService {
    ResponseEntity<?> findProduct(Long barcode);
}

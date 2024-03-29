package com.solution.recipetalk.service.product;

import com.solution.recipetalk.dto.product.request.ProductRequestRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterProductService {
    ResponseEntity<?> registerRequestProduct(ProductRequestRegisterDTO dto);
}

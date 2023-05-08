package com.solution.recipetalk.controller.product;

import com.solution.recipetalk.annotation.CustomLength;
import com.solution.recipetalk.dto.product.request.ProductRequestRegisterDTO;
import com.solution.recipetalk.service.product.FindProductService;
import com.solution.recipetalk.service.product.RegisterProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final FindProductService findProductService;
    private final RegisterProductService registerProductService;

    @GetMapping("/product/{barcode}")
    public ResponseEntity<?> productDetails(@PathVariable(name = "barcode") @CustomLength(max = 13, message = "barcode") Long barcode) {
        return findProductService.findProduct(barcode);
    }

    @PostMapping("/product")
    public ResponseEntity<?> productAdd(@RequestBody @Valid ProductRequestRegisterDTO dto) {
        return registerProductService.registerRequestProduct(dto);
    }
}

package com.solution.recipetalk.controller.product;

import com.solution.recipetalk.dto.product.request.ProductRequestRegisterDTO;
import com.solution.recipetalk.service.product.ProductService;
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
    private final ProductService productService;

    @GetMapping("/product/{barcode}")
    public ResponseEntity<?> productDetails(@PathVariable(name = "barcode") String barcode) {
        return productService.findProduct(barcode);
    }

    @PostMapping("/product")
    public ResponseEntity<?> productAdd(@RequestBody @Valid ProductRequestRegisterDTO dto) {
        return productService.registerRequestProduct(dto);
    }
}

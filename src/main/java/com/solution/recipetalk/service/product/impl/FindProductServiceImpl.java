package com.solution.recipetalk.service.product.impl;

import com.solution.recipetalk.domain.product.entity.Product;
import com.solution.recipetalk.domain.product.repository.ProductRepository;
import com.solution.recipetalk.dto.product.ProductResponseDTO;
import com.solution.recipetalk.exception.product.ProductNotFoundException;
import com.solution.recipetalk.service.product.FindProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindProductServiceImpl implements FindProductService {
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<?> findProduct(Long barcode) {
        Product product = productRepository.findById(barcode).orElseThrow(ProductNotFoundException::new);

        return ResponseEntity.ok(ProductResponseDTO.toDTO(product));
    }
}

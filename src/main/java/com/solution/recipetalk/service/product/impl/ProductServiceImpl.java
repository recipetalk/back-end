package com.solution.recipetalk.service.product.impl;

import com.solution.recipetalk.domain.product.entity.Product;
import com.solution.recipetalk.domain.product.repository.ProductRepository;
import com.solution.recipetalk.domain.product.request.repository.ProductRequestRepository;
import com.solution.recipetalk.dto.product.ProductResponseDTO;
import com.solution.recipetalk.dto.product.request.ProductRequestRegisterDTO;
import com.solution.recipetalk.exception.product.BarcodeLengthNotValidException;
import com.solution.recipetalk.exception.product.ProductNotFoundException;
import com.solution.recipetalk.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductRequestRepository productRequestRepository;

    @Override
    public ResponseEntity<?> findProduct(String barcode) {
        validateBarcodeLength(barcode);

        Long barcodeStringToLong = Long.parseLong(barcode);
        Product product = productRepository.findById(barcodeStringToLong).orElseThrow(ProductNotFoundException::new);

        return ResponseEntity.ok(ProductResponseDTO.toDTO(product));
    }

    private void validateBarcodeLength(String barcode) {
        if(barcode.length() != 13) {
            throw new BarcodeLengthNotValidException();
        }
    }

    @Override
    public ResponseEntity<?> registerRequestProduct(ProductRequestRegisterDTO dto) {
        productRequestRepository.save(dto.toEntity());
        return ResponseEntity.ok(null);
    }
}

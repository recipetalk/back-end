package com.solution.recipetalk.service.product.impl;

import com.solution.recipetalk.domain.product.request.repository.ProductRequestRepository;
import com.solution.recipetalk.dto.product.request.ProductRequestRegisterDTO;
import com.solution.recipetalk.service.product.RegisterProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterProductServiceImpl implements RegisterProductService {
    private final ProductRequestRepository productRequestRepository;

    @Override
    public ResponseEntity<?> registerRequestProduct(ProductRequestRegisterDTO dto) {
        productRequestRepository.save(dto.toEntity());
        return ResponseEntity.ok(null);
    }
}

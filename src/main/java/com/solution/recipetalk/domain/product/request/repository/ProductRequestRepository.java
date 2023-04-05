package com.solution.recipetalk.domain.product.request.repository;

import com.solution.recipetalk.domain.product.request.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRequestRepository extends JpaRepository<ProductRequest, Long> {
}

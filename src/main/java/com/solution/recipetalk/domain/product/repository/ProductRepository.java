package com.solution.recipetalk.domain.product.repository;

import com.solution.recipetalk.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {
}

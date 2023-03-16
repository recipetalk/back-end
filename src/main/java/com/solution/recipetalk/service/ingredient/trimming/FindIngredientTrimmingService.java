package com.solution.recipetalk.service.ingredient.trimming;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface FindIngredientTrimmingService {
    ResponseEntity<?> findIngredientTrimming(Long ingredientId, Pageable pageable);
    ResponseEntity<?> findIngredientTrimmingDetail(Long ingredientId, Long trimmingId);
}

package com.solution.recipetalk.service.ingredient;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FindIngredientService {
    ResponseEntity<?> findIngredientListByNameComponentPage(String nameComponent, Pageable pageable);
}

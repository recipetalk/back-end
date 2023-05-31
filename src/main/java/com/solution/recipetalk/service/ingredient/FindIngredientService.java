package com.solution.recipetalk.service.ingredient;

import org.springframework.http.ResponseEntity;

public interface FindIngredientService {
    ResponseEntity<?> findIngredientListByNameComponentPage(String nameComponent);
}

package com.solution.recipetalk.service.ingredient;

import org.springframework.http.ResponseEntity;

public interface FindIngredientService {
    ResponseEntity<?> findIngredientListByNameComponent(String nameComponent);
    ResponseEntity<?> findIngredientListByNameComponentPage(String nameComponent);
}

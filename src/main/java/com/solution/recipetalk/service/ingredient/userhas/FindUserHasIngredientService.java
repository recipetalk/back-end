package com.solution.recipetalk.service.ingredient.userhas;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FindUserHasIngredientService {
    ResponseEntity<?> findUserHasIngredient(Long userHasIngredientId);
    ResponseEntity<?> findUserHasIngredients(Pageable pageable, String sortElement);


}

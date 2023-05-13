package com.solution.recipetalk.service.ingredient.trimming;

import org.springframework.http.ResponseEntity;

public interface RemoveIngredientTrimmingService {
    ResponseEntity<?> removeIngredientTrimmingById(Long trimmingId);

    ResponseEntity<?> hardRemoveIngredientTrimmingById(Long trimmingId);
}

package com.solution.recipetalk.service.ingredient.trimming.row;

import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowRegisterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegisterIngredientTrimmingRowService {

    ResponseEntity<?> registerIngredientTrimmingRow(IngredientTrimmingRowRegisterDTO dto, Long ingredientTrimmingId);

    ResponseEntity<?> registerIngredientTrimmingRow(IngredientTrimmingRowRegisterDTO dto, IngredientTrimming ingredientTrimming);

}

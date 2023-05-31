package com.solution.recipetalk.service.ingredient.trimming.row;

import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowModifyDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EditIngredientTrimmingRowService {
    ResponseEntity<?> editIngredientTrimmingRow(IngredientTrimmingRowModifyDTO dto, Long trimmingId);
}

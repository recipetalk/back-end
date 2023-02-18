package com.solution.recipetalk.controller.ingredient.trimming.row;

import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowRegisterDTO;
import com.solution.recipetalk.service.ingredient.trimming.row.RegisterIngredientTrimmingRowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/ingredient/{ingredientId}/trimming/{trimmingId}")
public class IngredientTrimmingRowController {
    private final RegisterIngredientTrimmingRowService ingredientTrimmingRowService;

    @PostMapping("")
    public ResponseEntity<?> ingredientTrimmingRowAddAll(@PathVariable(name = "trimmingId")Long trimmingId, List<IngredientTrimmingRowRegisterDTO> dtos){
        return ingredientTrimmingRowService.registerIngredientTrimmingRow(dtos,trimmingId);
    }
}

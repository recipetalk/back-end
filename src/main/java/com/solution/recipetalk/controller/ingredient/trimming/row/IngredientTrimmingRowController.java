package com.solution.recipetalk.controller.ingredient.trimming.row;

import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingModifyListDTO;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowRegisterDTO;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowRegisterListDTO;
import com.solution.recipetalk.service.ingredient.trimming.row.EditIngredientTrimmingRowService;
import com.solution.recipetalk.service.ingredient.trimming.row.RegisterIngredientTrimmingRowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/ingredient/{ingredientId}/trimming/{trimmingId}")
public class IngredientTrimmingRowController {
    private final RegisterIngredientTrimmingRowService ingredientTrimmingRowService;
    private final EditIngredientTrimmingRowService editIngredientTrimmingRowService;

    @PostMapping("")
    public ResponseEntity<?> ingredientTrimmingRowAddAll(@PathVariable(name = "trimmingId")Long trimmingId, IngredientTrimmingRowRegisterDTO dto){
        return ingredientTrimmingRowService.registerIngredientTrimmingRow(dto, trimmingId);
    }

    @PatchMapping("/row")
    public ResponseEntity<?> ingredientTrimmingRowModify(@PathVariable(name = "trimmingId")Long trimmingId, IngredientTrimmingModifyListDTO dtos){
        return editIngredientTrimmingRowService.editIngredientTrimmingRow(dtos.getDtos(), trimmingId);
    }
}

package com.solution.recipetalk.controller.ingredient.trimming;

import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingRegisterDTO;
import com.solution.recipetalk.service.ingredient.trimming.RegisterIngredientTrimmingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/ingredient/{ingredientId}/trimming")
public class IngredientTrimmingController {
    private final RegisterIngredientTrimmingService ingredientTrimmingService;

    @PostMapping("")
    public ResponseEntity<?> ingredientTrimmingAdd(@PathVariable(name = "ingredientId") Long ingredientId, @Valid IngredientTrimmingRegisterDTO dto){
        return ingredientTrimmingService.registerIngredientTrimming(dto, ingredientId);
    }
}

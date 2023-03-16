package com.solution.recipetalk.controller.ingredient.trimming;

import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingModifyDTO;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingRegisterDTO;
import com.solution.recipetalk.service.ingredient.trimming.EditIngredientTrimmingService;
import com.solution.recipetalk.service.ingredient.trimming.FindIngredientTrimmingService;
import com.solution.recipetalk.service.ingredient.trimming.RegisterIngredientTrimmingService;
import com.solution.recipetalk.service.ingredient.trimming.RemoveIngredientTrimmingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/ingredient/{ingredientId}/trimming")
public class IngredientTrimmingController {
    private final RegisterIngredientTrimmingService ingredientTrimmingService;
    private final FindIngredientTrimmingService findIngredientTrimmingService;
    private final RemoveIngredientTrimmingService removeIngredientTrimmingService;
    private final EditIngredientTrimmingService editIngredientTrimmingService;

    @PostMapping("")
    public ResponseEntity<?> ingredientTrimmingAdd(@PathVariable(name = "ingredientId") Long ingredientId, @Valid IngredientTrimmingRegisterDTO dto) {
        return ingredientTrimmingService.registerIngredientTrimming(dto, ingredientId);
    }

    @GetMapping("")
    public ResponseEntity<?> ingredientTrimmingList(@PathVariable(name = "ingredientId") Long ingredientId, @PageableDefault(size = 20) Pageable pageable) {
        return findIngredientTrimmingService.findIngredientTrimming(ingredientId, pageable);
    }

    @GetMapping("/{trimmingId}")
    public ResponseEntity<?> ingredientTrimmingDetail(@PathVariable(name = "ingredientId") Long ingredientId, @PathVariable(name = "trimmingId") Long trimmingId) {
        return findIngredientTrimmingService.findIngredientTrimmingDetail(ingredientId, trimmingId);
    }

    @DeleteMapping("/{trimmingId}")
    public ResponseEntity<?> ingredientTrimmingRemoveByTrimmingId(@PathVariable(name = "trimmingId") Long trimmingId){
        return removeIngredientTrimmingService.removeIngredientTrimmingById(trimmingId);
    }

    @PatchMapping("/{trimmingId}")
    public ResponseEntity<?> ingredientTrimmingModifyByTrimmingId(@PathVariable(name = "trimmingId") Long trimmingId, @Valid IngredientTrimmingModifyDTO dto){
        return editIngredientTrimmingService.editIngredientTrimming(dto, trimmingId);
    }
}

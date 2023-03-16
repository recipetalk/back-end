package com.solution.recipetalk.controller.ingredient.description;

import com.solution.recipetalk.dto.ingredient.description.IngredientDescriptionModifyDTO;
import com.solution.recipetalk.dto.ingredient.description.IngredientDescriptionRegisterDTO;
import com.solution.recipetalk.service.ingredient.description.FindIngredientDescriptionService;
import com.solution.recipetalk.service.ingredient.description.ModifyIngredientDescriptionService;
import com.solution.recipetalk.service.ingredient.description.RegisterIngredientDescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/ingredient/{ingredientId}/description")
public class IngredientDescriptionController {
    private final RegisterIngredientDescriptionService registerIngredientDescriptionService;
    private final FindIngredientDescriptionService findIngredientDescriptionService;

    private final ModifyIngredientDescriptionService modifyIngredientDescriptionService;

    @PostMapping("")
    public ResponseEntity<?> ingredientDescriptionAdd(
            @PathVariable(name = "ingredientId") Long ingredientId,
            @Valid IngredientDescriptionRegisterDTO dto
            ) {
        return registerIngredientDescriptionService.addIngredientDescription(dto, ingredientId);
    }

    @GetMapping("/details")
    public ResponseEntity<?> ingredientDescriptionDetails(
            @PathVariable(name = "ingredientId") Long ingredientId
    ) {
        return findIngredientDescriptionService.findIngredientDescription(ingredientId);
    }

    @PatchMapping("")
    public ResponseEntity<?> ingredientDescriptionModify(
            @PathVariable(name = "ingredientId") Long ingredientId,
            IngredientDescriptionModifyDTO dto
            ) {
        return modifyIngredientDescriptionService.modifyIngredientDescription(ingredientId, dto);
    }
}

package com.solution.recipetalk.controller.recipe;


import com.solution.recipetalk.dto.recipe.ingredient.RecipeIngredientRegisterDTO;
import com.solution.recipetalk.service.recipe.ingredient.RegisterRecipeIngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/board/recipe")
public class RecipeIngredientController {
    private final RegisterRecipeIngredientService registerRecipeIngredientService;

    @PostMapping("/{recipeId}/recipeIngredient")
    public ResponseEntity<?> recipeIngredientAdd(@PathVariable(name = "recipeId") Long recipeId, @Valid @RequestBody List<RecipeIngredientRegisterDTO> dtos){
        return registerRecipeIngredientService.registerRecipeIngredient(recipeId, dtos);
    }
}

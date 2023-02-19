package com.solution.recipetalk.controller.recipe;

import com.solution.recipetalk.dto.recipe.RecipeRegisterDTO;
import com.solution.recipetalk.service.recipe.FindRecipeService;
import com.solution.recipetalk.service.recipe.RegisterRecipeService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/board/recipe")
public class RecipeController {
    private final RegisterRecipeService registerRecipeService;
    private final FindRecipeService findRecipeService;

    @PostMapping("")
    public ResponseEntity<?> recipeAdd(@Valid @NonNull RecipeRegisterDTO dto) {
        return registerRecipeService.addRecipe(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> recipeDetails(@PathVariable(name = "id") Long recipeId){
        return findRecipeService.findRecipeWithId(recipeId);
    }
}

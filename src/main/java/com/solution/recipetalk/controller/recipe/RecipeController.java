package com.solution.recipetalk.controller.recipe;

import com.solution.recipetalk.dto.recipe.RecipeRegisterDTO;
import com.solution.recipetalk.service.recipe.RegisterRecipeService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/board/recipe")
public class RecipeController {
    private final RegisterRecipeService registerRecipeService;

    @PostMapping("")
    public ResponseEntity<?> recipeAdd(@RequestBody @Valid @NonNull RecipeRegisterDTO dto) {
        return registerRecipeService.addRecipe(dto);
    }
}

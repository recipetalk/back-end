package com.solution.recipetalk.controller.recipe;


import com.solution.recipetalk.dto.recipe.ingredient.RecipeIngredientDTOWrapper;
import com.solution.recipetalk.service.ingredient.FindIngredientService;
import com.solution.recipetalk.service.recipe.ingredient.FindRecipeIngredientService;
import com.solution.recipetalk.service.recipe.ingredient.ModifyRecipeIngredientService;
import com.solution.recipetalk.service.recipe.ingredient.RegisterRecipeIngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/board/recipe")
public class RecipeIngredientController {
    private final RegisterRecipeIngredientService registerRecipeIngredientService;
    private final ModifyRecipeIngredientService modifyRecipeIngredientService;
    private final FindRecipeIngredientService findRecipeIngredientService;
    private final FindIngredientService findIngredientService;

    @PostMapping("/{recipeId}/recipeIngredient")
    public ResponseEntity<?> recipeIngredientAdd(@PathVariable(name = "recipeId") Long recipeId, @Valid @RequestBody RecipeIngredientDTOWrapper wrapper){
        return registerRecipeIngredientService.registerRecipeIngredient(recipeId, wrapper.getRecipeIngredientRegisterDTOS());
    }

    @PutMapping("/{recipeId}/recipeIngredient")
    public ResponseEntity<?> recipeIngredientModify(@PathVariable(name = "recipeId") Long recipeId, @Valid @RequestBody RecipeIngredientDTOWrapper wrapper){
        return modifyRecipeIngredientService.modifyRecipeIngredient(recipeId, wrapper.getRecipeIngredientRegisterDTOS());
    }

    @GetMapping("/{recipeId}/recipeIngredient")
    public ResponseEntity<?> recipeIngredientList(@PathVariable(name = "recipeId") Long recipeId){
        return findRecipeIngredientService.findRecipeIngredient(recipeId);
    }

}

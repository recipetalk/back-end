package com.solution.recipetalk.service.recipe.ingredient.impl;

import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.dto.recipe.ingredient.RecipeIngredientRegisterDTO;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.service.recipe.ingredient.ModifyRecipeIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyRecipeIngredientServiceImpl implements ModifyRecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeRepository recipeRepository;

    private final IngredientRepository ingredientRepository;

    @Override
    public ResponseEntity<?> modifyRecipeIngredient(Long recipeId, List<RecipeIngredientRegisterDTO> dtos) {
        Recipe findRecipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        List<RecipeIngredient> recipeIngredientsByRecipeId = recipeIngredientRepository.findRecipeIngredientsByRecipeId(recipeId);
        recipeIngredientRepository.deleteAll(recipeIngredientsByRecipeId);

        List<RecipeIngredient> newRecipeIngredientList = dtos.stream().map(dto -> dto.toRecipeIngredientEntity(
                        ingredientRepository.findById(dto.getIngredientId()).orElseThrow(IngredientNotFoundException::new),
                        findRecipe
                )
        ).toList();
        recipeIngredientRepository.saveAll(newRecipeIngredientList);

        return ResponseEntity.ok(null);
    }
}

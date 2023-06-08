package com.solution.recipetalk.service.recipe.ingredient.impl;

import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.dto.recipe.ingredient.RecipeIngredientRegisterDTO;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.service.recipe.ingredient.RegisterRecipeIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class RegisterRecipeIngredientServiceImpl implements RegisterRecipeIngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public ResponseEntity<?> registerRecipeIngredient(Long recipeId, List<RecipeIngredientRegisterDTO> dtos) {
        List<RecipeIngredient> newRecipeIngredientList = dtos.stream().map(dto -> {
            Ingredient ingredient = null;
                if(dto.getIngredientId() != null) {
                    ingredient = ingredientRepository.findById(dto.getIngredientId()).orElseThrow(IngredientNotFoundException::new);
                    ingredient.countUp();
                }
                return dto.toRecipeIngredientEntity(
                        ingredient,
                        recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new)
                );
            }
        ).toList();
        recipeIngredientRepository.saveAll(newRecipeIngredientList);

        return ResponseEntity.ok(null);
    }
}

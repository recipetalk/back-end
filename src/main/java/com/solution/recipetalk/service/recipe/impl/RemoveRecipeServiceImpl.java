package com.solution.recipetalk.service.recipe.impl;

import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.img.entity.RecipeRowImg;
import com.solution.recipetalk.domain.recipe.row.img.repository.RecipeRowImgRepository;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.service.recipe.RemoveRecipeService;
import com.solution.recipetalk.service.recipe.row.RemoveRecipeRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveRecipeServiceImpl implements RemoveRecipeService {
    private final RecipeRepository recipeRepository;
    private final BoardRepository boardRepository;
    private final RecipeRowRepository recipeRowRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RemoveRecipeRowService removeRecipeRowService;

    @Override
    public ResponseEntity<?> removeRecipeById(Long recipeId){
        Long loginUser = ContextHolder.getUserLoginId();
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        Long recipeWriterId = recipe.getBoard().getWriter().getId();

        // TODO: exception
        if (!Objects.equals(loginUser, recipeWriterId)){
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findRecipeIngredientsByRecipeId(recipeId);
        List<RecipeRow> recipeRows = recipeRowRepository.findRecipeRowsByRecipeIdOrderById(recipeId);

        // TODO: removeRecipeRow 개선 (권한 검증 생략 + recipeRow 객체로 받아서 삭제 처리)
        recipeRows.forEach(recipeRow -> removeRecipeRowService.removeRecipeRow(recipeId, recipeRow.getId()));
        recipeIngredientRepository.deleteAll(recipeIngredients);
        recipeRepository.delete(recipe);
        boardRepository.delete(recipe.getBoard());

        return ResponseEntity.ok(null);
    }
}

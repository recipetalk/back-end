package com.solution.recipetalk.service.recipe.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.dto.board.BoardDTO;
import com.solution.recipetalk.dto.recipe.RecipeDTO;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.service.recipe.FindRecipeService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindRecipeServiceImpl implements FindRecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public ResponseEntity<?> findRecipeWithId(Long recipeId) {
        Optional<RecipeDTO> recipeByViewerId = recipeRepository.findRecipeByViewerId(ContextHolder.getUserLoginId(), recipeId);

        if(recipeByViewerId.isPresent())
            return ResponseEntity.ok(recipeByViewerId.get());
        else
            return ResponseEntity.notFound().build();
    }


}

package com.solution.recipetalk.service.recipe.impl;

import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.service.recipe.RemoveRecipeService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveRecipeServiceImpl implements RemoveRecipeService {
    private final RecipeRepository recipeRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<?> removeRecipeById(Long recipeId){
        Long loginUser = ContextHolder.getUserLoginId();
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        Long recipeWriterId = recipe.getBoard().getWriter().getId();

        if (!Objects.equals(loginUser, recipeWriterId)){
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        recipeRepository.delete(recipe);
        boardRepository.delete(recipe.getBoard());

        return ResponseEntity.ok(null);
    }
}

package com.solution.recipetalk.service.recipe.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.dto.recipe.RecipeModifyDTO;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.service.recipe.ModifyRecipeService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyRecipeServiceImpl implements ModifyRecipeService {
    private final RecipeRepository recipeRepository;

    public ResponseEntity<?> modifyRecipe(Long recipeId, RecipeModifyDTO dto){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        Board board = recipe.getBoard();
        Long recipeWriterId = board.getWriter().getId();
        Long loginUserId = ContextHolder.getUserLoginId();

        if (!Objects.equals(recipeWriterId, loginUserId)){
            // TODO: exception
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        board.changeTitle(dto.getTitle());
        recipe.changeByRecipeModifyDTO(dto, board);

        return ResponseEntity.ok(null);
    }
}

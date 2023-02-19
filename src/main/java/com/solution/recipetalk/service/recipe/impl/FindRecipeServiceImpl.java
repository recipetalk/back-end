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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindRecipeServiceImpl implements FindRecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public ResponseEntity<?> findRecipeWithId(Long recipeId) {
        Recipe findRecipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        Board findRecipeBoard = findRecipe.getBoard();
        UserDetail recipeWriter = findRecipeBoard.getWriter();

        //증가하고 보여주는 걸로..?
        findRecipeBoard.increaseViewCount();

        UserSimpleProfileDTO profileDTO = UserSimpleProfileDTO.toDTO(recipeWriter);

        BoardDTO boardDTO = BoardDTO.toDTO(findRecipeBoard, profileDTO);

        RecipeDTO recipeDTO = RecipeDTO.toDTO(findRecipe, boardDTO);


        return ResponseEntity.ok(recipeDTO);
    }


}

package com.solution.recipetalk.service.recipe.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.recipe.RecipeRegisterDTO;
import com.solution.recipetalk.dto.recipe.RecipeRegisterResDTO;
import com.solution.recipetalk.service.recipe.RegisterRecipeService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterRecipeServiceImpl implements RegisterRecipeService {

    private final BoardRepository boardRepository;
    private final RecipeRepository recipeRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> addRecipe(RecipeRegisterDTO recipeRegisterDTO) {
        Long writerId = ContextHolder.getUserLoginId();
        UserDetail writer = userDetailRepository.getReferenceById(writerId);

        Board newBoard = recipeRegisterDTO.toBoardEntity(writer);

        boardRepository.save(newBoard);

        String thumbnailURI = "";

        Recipe newRecipe = recipeRegisterDTO.toRecipeEntity(thumbnailURI, newBoard);
        newRecipe = recipeRepository.save(newRecipe);


        return ResponseEntity.ok(
                RecipeRegisterResDTO.builder()
                        .recipeId(newRecipe.getId())
        );
    }
}

package com.solution.recipetalk.service.recipe.impl;

import com.solution.recipetalk.domain.recipe.query.RecipeForList;
import com.solution.recipetalk.domain.recipe.repository.RecipeByUsername;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.board.BoardDTO;
import com.solution.recipetalk.dto.board.BoardRecipeDTO;
import com.solution.recipetalk.dto.recipe.RecipeByUserResDTO;
import com.solution.recipetalk.dto.recipe.RecipeDTO;
import com.solution.recipetalk.dto.recipe.RecipeListReqDTO;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.recipe.FindRecipeService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindRecipeServiceImpl implements FindRecipeService {

    private final RecipeRepository recipeRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> findRecipeWithId(Long recipeId) {
        Optional<RecipeDTO> recipeByViewerId = recipeRepository.findRecipeByViewerId(ContextHolder.getUserLoginId(), recipeId);

        if(recipeByViewerId.isPresent())
            return ResponseEntity.ok(recipeByViewerId.get());
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> findRecipeList(RecipeListReqDTO dto){
        Long userLoginId = ContextHolder.getUserLoginId();
        UserDetail currentUser = userDetailRepository.findById(userLoginId).orElseThrow(UserNotFoundException::new);
        List<RecipeForList> recipes = recipeRepository.findRecipeList(dto, currentUser.getId());

        List<RecipeDTO> recipeDTOList = recipes.stream().map(recipe -> {
            UserSimpleProfileDTO userSimpleProfileDTO = UserSimpleProfileDTO.toDTO(recipe.getRecipe().getBoard().getWriter());
            BoardDTO boardDTO = BoardDTO.toDTO(recipe.getRecipe().getBoard(), userSimpleProfileDTO, recipe.isLiked(), recipe.isBookmarked());
            return RecipeDTO.toDTO(recipe.getRecipe(), boardDTO);
        }).toList();

        return ResponseEntity.ok(recipeDTOList);
    }

    @Override
    public ResponseEntity<?> findRecipeWithUsername(String username){
        UserDetail user = userDetailRepository.findUserDetailByUsername(username).orElseThrow(UserNotFoundException::new);
        // TODO: exception (RecipeNotFoundException)
        List<RecipeByUsername> recipes = recipeRepository.findRecipeByUserId(user.getId()).orElseThrow(UserNotFoundException::new);

        List<RecipeByUserResDTO> recipeByUserResDTOList = recipes.stream().map(recipe -> {
            BoardRecipeDTO boardRecipeDTO = BoardRecipeDTO.toDTO(recipe);
            return RecipeByUserResDTO.ToRecipeByUserResDTO(boardRecipeDTO, recipe);
        }).toList();

        return ResponseEntity.ok(recipeByUserResDTOList);
    }


}

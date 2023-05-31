package com.solution.recipetalk.service.recipe.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.common.NotAuthorizedToRemoveException;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.board.RemoveBoardService;
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

    private final RemoveBoardService removeBoardService;

    private final S3Uploader s3Uploader;




    @Override
    public ResponseEntity<?> removeRecipeById(Long recipeId){
        Long loginUser = ContextHolder.getUserLoginId();
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);
        Board board = boardRepository.findById(recipeId).orElseThrow(BoardNotFoundException::new);
        Long recipeWriterId = recipe.getBoard().getWriter().getId();

        if (!Objects.equals(loginUser, recipeWriterId)){
            throw new NotAuthorizedToRemoveException();
        }

        List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findRecipeIngredientsByRecipeId(recipeId);
        List<RecipeRow> recipeRows = recipeRowRepository.findRecipeRowsByRecipeIdOrderById(recipeId);

        // TODO: removeRecipeRow 개선 (권한 검증 생략 + recipeRow 객체로 받아서 삭제 처리)
        recipeRows.forEach(recipeRow -> removeRecipeRowService.removeRecipeRowNoWriterCheckingById(recipeRow.getId()));
        recipeIngredientRepository.deleteAll(recipeIngredients);
        recipeRepository.delete(recipe);
        boardRepository.delete(board);

        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<?> hardRemoveRecipeById(Long recipeId) { // 정상적으로 등록 안되었을 경우 익셉션 처리
        Board board = boardRepository.findById(recipeId).orElseThrow(BoardNotFoundException::new);

        if(!Objects.equals(board.getWriter().getId(), ContextHolder.getUserLoginId())){
            throw new NotAuthorizedException();
        }
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);

        if(recipe.getThumbnailImgURI() != null)
            s3Uploader.deleteFile(recipe.getThumbnailImgURI(), S3dir.RECIPE_IMG_DIR);


        List<RecipeRow> recipeRows = recipeRowRepository.findRecipeRowsByRecipeIdOrderById(recipeId);
        List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findRecipeIngredientsByRecipeId(recipeId);

        recipeRows.forEach(recipeRow -> removeRecipeRowService.removeRecipeRowNoWriterCheckingById(recipeRow.getId()));
        recipeIngredientRepository.deleteAll(recipeIngredients);

        recipeRepository.hardDeleteRecipeById(recipeId);

        removeBoardService.hardRemoveByBoardId(recipeId);

        return ResponseEntity.ok(null);
    }
}

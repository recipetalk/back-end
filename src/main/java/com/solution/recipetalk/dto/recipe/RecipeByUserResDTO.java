package com.solution.recipetalk.dto.recipe;

import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.query.RecipeForList;
import com.solution.recipetalk.domain.recipe.repository.RecipeByUsername;
import com.solution.recipetalk.dto.board.BoardRecipeDTO;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeByUserResDTO {
    private BoardRecipeDTO board;
    private String thumbnailUri;
    private String quantity;
    private Long recipeId;

    public static RecipeByUserResDTO ToRecipeByUserResDTO(BoardRecipeDTO boardRecipeDTO, RecipeForList recipeForList){
        Recipe recipe = recipeForList.getRecipe();
        return RecipeByUserResDTO.builder()
                .board(boardRecipeDTO)
                .thumbnailUri(recipe.getThumbnailImgURI())
                .quantity(recipe.getQuantity().toString())
                .recipeId(recipe.getId())
                .build();
    }
}

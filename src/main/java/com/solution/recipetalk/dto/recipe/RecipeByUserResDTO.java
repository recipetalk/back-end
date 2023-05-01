package com.solution.recipetalk.dto.recipe;

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

    public static RecipeByUserResDTO ToRecipeByUserResDTO(BoardRecipeDTO boardRecipeDTO, RecipeByUsername recipeByUsername){
        return RecipeByUserResDTO.builder()
                .board(boardRecipeDTO)
                .thumbnailUri(recipeByUsername.getThumbnailImgURI())
                .quantity(recipeByUsername.getQuantity())
                .recipeId(recipeByUsername.getRecipeId())
                .build();
    }
}

package com.solution.recipetalk.dto.recipe;

import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.dto.board.BoardDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDTO {
    private BoardDTO board;
    private String description;
    private String thumbnailUri;
    private String quantity;

    public static RecipeDTO toDTO(Recipe recipe, BoardDTO boardDTO){
        return RecipeDTO.builder()
                .board(boardDTO)
                .description(recipe.getDescription())
                .quantity(recipe.getQuantity()+"인분")
                .thumbnailUri(recipe.getThumbnailImgURI())
                .build();
    }
}

package com.solution.recipetalk.dto.ingredient.trimming;

import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.dto.board.BoardIngredientDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientTrimmingByUserResDTO {
    private BoardIngredientDTO board;
    private String description;
    private String thumbnailUri;

    public static IngredientTrimmingByUserResDTO toIngredientTrimmingByUserResDTO(IngredientTrimming ingredientTrimming){
        return IngredientTrimmingByUserResDTO.builder()
                .board(BoardIngredientDTO.toBoardIngredientDTO(ingredientTrimming.getBoard()))
                .description(ingredientTrimming.getDescription())
                .thumbnailUri(ingredientTrimming.getThumbnailUri())
                .build();
    }
}

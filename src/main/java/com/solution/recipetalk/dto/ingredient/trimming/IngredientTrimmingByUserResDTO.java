package com.solution.recipetalk.dto.ingredient.trimming;

import com.querydsl.core.Tuple;
import com.solution.recipetalk.domain.board.entity.QBoard;
import com.solution.recipetalk.domain.ingredient.entity.QIngredient;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.entity.QIngredientTrimming;
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
    private Long ingredientId;

    public static IngredientTrimmingByUserResDTO toIngredientTrimmingByUserResDTO(Tuple tuple){
        return IngredientTrimmingByUserResDTO.builder()
                .board(BoardIngredientDTO.toBoardIngredientDTO(tuple.get(QBoard.board)))
                .description(tuple.get(QIngredientTrimming.ingredientTrimming).getDescription())
                .thumbnailUri(tuple.get(QIngredientTrimming.ingredientTrimming).getThumbnailUri())
                .ingredientId(tuple.get(QIngredient.ingredient).getId())
                .build();
    }
}

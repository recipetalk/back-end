package com.solution.recipetalk.dto.ingredient.trimming;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngredientTrimmingRegisterDTO {
    @NonNull
    private String title;

    private MultipartFile thumbnail;


    public IngredientTrimming toIngredientTrimming(Board board, Ingredient ingredient, String thumbnailUri){
        return IngredientTrimming.builder()
                .ingredient(ingredient)
                .thumbnailUri(thumbnailUri)
                .board(board)
                .build();
    }

    public Board toBoardEntity(UserDetail writer){
        return Board.builder()
                .title(title)
                .writer(writer)
                .view_count(0L)
                .build();
    }
}

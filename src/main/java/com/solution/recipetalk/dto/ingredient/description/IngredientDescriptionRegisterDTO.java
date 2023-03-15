package com.solution.recipetalk.dto.ingredient.description;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.ingredient.description.entity.IngredientDescription;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientDescriptionRegisterDTO {
    @NonNull
    private String title;

    private MultipartFile imgFile;
    @NonNull
    @Size(min = 1, max = 1000, message = "입력할 수 있는 크기를 초과했습니다.")
    private String description;

    public IngredientDescription toIngredientDescription(Board board, Ingredient ingredient, String imgURI, String description) {
        return IngredientDescription.builder()
                .board(board)
                .ingredient(ingredient)
                .imgURI(imgURI)
                .description(description)
                .build();
    }

    public Board toBoardEntity(UserDetail writer) {
        return Board.builder()
                .title(title)
                .writer(writer)
                .view_count(0L)
                .build();
    }
}

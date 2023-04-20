package com.solution.recipetalk.dto.recipe;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.entity.RecipeQuantityCategory;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeRegisterDTO {
    @NonNull
    private String title;

    //TODO : Multipartfile Valid 처리 방법 고안하기.
    private MultipartFile thumbnail;
    @NonNull
    private String description;
    @NonNull
    private String quantity;

    public Board toBoardEntity(UserDetail writer) {
        return Board.builder()
                .title(title)
                .writer(writer)
                .likeCount(0L)
                .commentCount(0L)
                .build();
    }

    public Recipe toRecipeEntity(String thumbnailURI, Board board) {
        return Recipe.builder()
                .thumbnailImgURI(thumbnailURI)
                .board(board)
                .quantity(RecipeQuantityCategory.valueOf(quantity))
                .description(description)
                .build();

    }
}

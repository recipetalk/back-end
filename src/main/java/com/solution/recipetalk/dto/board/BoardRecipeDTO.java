package com.solution.recipetalk.dto.board;

import com.solution.recipetalk.domain.recipe.repository.RecipeByUsername;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRecipeDTO {
    private String title;
    private LocalDateTime createdDate;
    private Boolean isLiked;
    private Long likeCount;
    private Long commentCount;
    private Long boardId;
    private String boardSort;


    public static BoardRecipeDTO toDTO(RecipeByUsername recipeByUsername){
        return BoardRecipeDTO.builder()
                .title(recipeByUsername.getTitle())
                .createdDate(recipeByUsername.getCreatedDate())
                .isLiked(recipeByUsername.getIsLiked())
                .likeCount(recipeByUsername.getLikeCount())
                .commentCount(recipeByUsername.getCommentCount())
                .boardId(recipeByUsername.getBoardId())
                .boardSort(recipeByUsername.getBoardSort().toString())
                .build();
    }
}

package com.solution.recipetalk.dto.board;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.query.RecipeForList;
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


    public static BoardRecipeDTO toDTO(RecipeForList recipeForList){
        Board board= recipeForList.getRecipe().getBoard();
        return BoardRecipeDTO.builder()
                .title(board.getTitle())
                .createdDate(board.getCreatedDate())
                .isLiked(recipeForList.getIsLiked())
                .likeCount(board.getLikeCount())
                .commentCount(board.getCommentCount())
                .boardId(board.getId())
                .boardSort(board.getBoardSort().toString())
                .build();
    }
}

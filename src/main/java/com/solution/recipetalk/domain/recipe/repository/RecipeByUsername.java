package com.solution.recipetalk.domain.recipe.repository;

import com.solution.recipetalk.domain.board.entity.BoardSort;

import java.time.LocalDateTime;

public interface RecipeByUsername {
    String getTitle();
    LocalDateTime getCreatedDate();
    Boolean getIsLiked();
    Long getLikeCount();
    Long getCommentCount();
    String getThumbnailImgURI();
    String getQuantity();
    BoardSort getBoardSort();
    Long getBoardId();
    Long getRecipeId();
}
package com.solution.recipetalk.domain.recipe.query;

import com.querydsl.core.Tuple;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.entity.QBoard;
import com.solution.recipetalk.domain.recipe.entity.QRecipe;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.user.entity.QUserDetail;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeForList {
    private Recipe recipe;
    private UserDetail writer;
    private Board board;
    private boolean isBookmarked;
    private boolean isLiked;
    private boolean isFollowing;

    static List<RecipeForList> toRecipeForList(List<Tuple> recipeRaws){
        return recipeRaws.stream().map(recipeRaw ->
            RecipeForList.builder()
                    .recipe(recipeRaw.get(QRecipe.recipe))
                    .writer(recipeRaw.get(QUserDetail.userDetail))
                    .board(recipeRaw.get(QBoard.board))
                    .isBookmarked(Boolean.TRUE.equals(recipeRaw.get(1, Boolean.class)))
                    .isLiked(Boolean.TRUE.equals(recipeRaw.get(2, Boolean.class)))
                    .isFollowing(Boolean.TRUE.equals(recipeRaw.get(3, Boolean.class)))
                    .build())
                .collect(Collectors.toList());
    }
}

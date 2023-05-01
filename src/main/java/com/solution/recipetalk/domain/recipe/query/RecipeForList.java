package com.solution.recipetalk.domain.recipe.query;

import com.querydsl.core.Tuple;
import com.solution.recipetalk.domain.recipe.entity.QRecipe;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
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
    private boolean isBookmarked;
    private boolean isLiked;

    static List<RecipeForList> toRecipeForList(List<Tuple> recipeRaws){
        return recipeRaws.stream().map(recipeRaw ->
            RecipeForList.builder()
                    .recipe(recipeRaw.get(QRecipe.recipe))
                    .isBookmarked(Boolean.TRUE.equals(recipeRaw.get(1, Boolean.class)))
                    .isLiked(Boolean.TRUE.equals(recipeRaw.get(2, Boolean.class)))
                    .build())
                .collect(Collectors.toList());
    }
}

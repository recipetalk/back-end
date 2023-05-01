package com.solution.recipetalk.dto.recipe;

import com.solution.recipetalk.domain.common.SortType;
import com.solution.recipetalk.domain.recipe.entity.RecipeSituationCategory;
import com.solution.recipetalk.domain.recipe.entity.RecipeSortCategory;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Nullable;

// TODO : Enum validate and exception handling
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeListReqDTO {
    @NonNull
    //@Length(max = 10)
    //@Enum(enumClass = SortType.class, ignoreCase = true)
    private SortType sortType;

    @Nullable
    //@Length(max = 30)
    //@Enum(enumClass = RecipeSortCategory.class, ignoreCase = true)
    private RecipeSortCategory sortCategory;

    @Nullable
    //@Length(max = 30)
    //@Enum(enumClass = RecipeSituationCategory.class, ignoreCase = true)
    private RecipeSituationCategory situationCategory;

    @Nullable
    @Length(max = 10000)
    private String title;

    //TODO: length validate
    @NonNull
    private Integer offset;

    @NonNull
    private Integer limit;
}

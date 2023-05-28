package com.solution.recipetalk.domain.recipe.repository;

import com.solution.recipetalk.domain.recipe.query.RecipeForList;
import com.solution.recipetalk.dto.recipe.RecipeByUserReqDTO;
import com.solution.recipetalk.dto.recipe.RecipeListReqDTO;

import java.util.List;

public interface RecipeQueryDslRepository {
    List<RecipeForList> findRecipeList(RecipeListReqDTO dto, Long userId);
}

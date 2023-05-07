package com.solution.recipetalk.domain.ingredient.trimming.repository;


import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingByUserReqDTO;

import java.util.List;

public interface IngredientTrimmingQueryDslRepository {
    List<IngredientTrimming> findIngredientTrimmingList(IngredientTrimmingByUserReqDTO dto, Long userId);
}

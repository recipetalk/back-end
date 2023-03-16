package com.solution.recipetalk.service.ingredient.trimming.row;

import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;

public interface RemoveIngredientTrimmingRowService {
    void removeIngredientTrimmingRowByIngredientTrimming(IngredientTrimming ingredientTrimming);
}

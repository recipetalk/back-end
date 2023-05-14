package com.solution.recipetalk.exception.recipe.row;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class RecipeRowNotFoundException extends CustomException {
    public RecipeRowNotFoundException() {
        super(ErrorCode.RECIPE_ROW_NOT_FOUND);
    }
}

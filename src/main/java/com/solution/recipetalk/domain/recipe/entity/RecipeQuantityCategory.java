package com.solution.recipetalk.domain.recipe.entity;

public enum RecipeQuantityCategory {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT_OVER(8);
    private final int value;

    RecipeQuantityCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

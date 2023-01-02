package com.solution.recipetalk.domain.ingredient.repository;

import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}

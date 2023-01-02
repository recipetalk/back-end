package com.solution.recipetalk.domain.ingredient.description.repository;

import com.solution.recipetalk.domain.ingredient.description.entity.IngredientDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientDescriptionRepository extends JpaRepository<IngredientDescription, Long> {
}

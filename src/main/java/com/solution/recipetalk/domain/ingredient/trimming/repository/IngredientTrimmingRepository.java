package com.solution.recipetalk.domain.ingredient.trimming.repository;

import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientTrimmingRepository extends JpaRepository<IngredientTrimming, Long> {
}

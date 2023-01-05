package com.solution.recipetalk.domain.ingredient.trimming.row.repository;

import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientTrimmingRowRepository extends JpaRepository<IngredientTrimmingRow, Long> {
}

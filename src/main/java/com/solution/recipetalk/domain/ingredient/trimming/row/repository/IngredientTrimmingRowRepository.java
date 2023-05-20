package com.solution.recipetalk.domain.ingredient.trimming.row.repository;

import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientTrimmingRowRepository extends JpaRepository<IngredientTrimmingRow, Long> {
    List<IngredientTrimmingRow> findAllByIngredientTrimming(IngredientTrimming ingredientTrimming);
    Optional<IngredientTrimmingRow> findByIngredientTrimmingAndTrimmingSeq(IngredientTrimming ingredientTrimming, Long trimmingSeq);
}

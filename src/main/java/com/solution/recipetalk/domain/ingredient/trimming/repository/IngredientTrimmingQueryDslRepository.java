package com.solution.recipetalk.domain.ingredient.trimming.repository;


import com.querydsl.core.Tuple;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingByUserReqDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientTrimmingQueryDslRepository {
    List<Tuple> findIngredientTrimmingList(IngredientTrimmingByUserReqDTO dto, Long userId);
}

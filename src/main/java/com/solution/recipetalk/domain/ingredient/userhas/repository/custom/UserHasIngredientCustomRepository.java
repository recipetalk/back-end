package com.solution.recipetalk.domain.ingredient.userhas.repository.custom;

import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserHasIngredientCustomRepository {
    Page<UserHasIngredientResponseDTO> findAllUserIngredient(Pageable pageable, Long startId, String sortElement);
}

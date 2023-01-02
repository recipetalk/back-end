package com.solution.recipetalk.domain.ingredient.userhas.repository;

import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHashIngredientRepository extends JpaRepository<UserHasIngredient, Long> {
}

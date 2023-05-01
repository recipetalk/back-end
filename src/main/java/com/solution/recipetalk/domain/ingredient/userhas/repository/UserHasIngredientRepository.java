package com.solution.recipetalk.domain.ingredient.userhas.repository;

import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.repository.custom.UserHasIngredientCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasIngredientRepository extends JpaRepository<UserHasIngredient, Long>, UserHasIngredientCustomRepository {
    
}

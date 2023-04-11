package com.solution.recipetalk.domain.recipe.repository;

import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    public Long countByBoard_Writer(UserDetail writer);
}

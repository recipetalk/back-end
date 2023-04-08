package com.solution.recipetalk.domain.ingredient.userhas.repository;

import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserHasIngredientRepository extends JpaRepository<UserHasIngredient, Long> {
    @Query(
            value = "select new com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientResponseDTO(i.name, uin.state, uin.quantity, uin.expirationDate, i.id) from UserHasIngredient uin join Ingredient i on uin.ingredient = i order by uin.expirationDate",
            countQuery = "select count(*) from UserHasIngredient uin"
    )
    Page<UserHasIngredientResponseDTO> findAllUserIngredient(Pageable pageable, Long startId);
}

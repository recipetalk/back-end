package com.solution.recipetalk.domain.ingredient.description.repository;

import com.solution.recipetalk.domain.ingredient.description.entity.IngredientDescription;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.dto.ingredient.description.IngredientDescriptionResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IngredientDescriptionRepository extends JpaRepository<IngredientDescription, Long> {
    @Query(value = "SELECT " +
            "new com.solution.recipetalk.dto.ingredient.description.IngredientDescriptionResponseDTO(board.title, ind.ingredient.name, ind.imgURI, ind.description, ind.createdDate <> ind.modifiedDate as isModified)" +
            "from IngredientDescription ind " +
            "join Board board on ind.board = board " +
            "where ind.ingredient.id = :ingredientId")
    Optional<IngredientDescriptionResponseDTO> findByIngredientId(@Param("ingredientId") Long ingredientId);

    Optional<IngredientDescription> findByIngredient(Ingredient ingredient);
}

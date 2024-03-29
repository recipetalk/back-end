package com.solution.recipetalk.domain.ingredient.repository;

import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.dto.ingredient.IngredientFindResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);

    @Query(value = "select new com.solution.recipetalk.dto.ingredient.IngredientFindResultDTO(i.name, i.id) " +
            "from Ingredient i " +
            "where i.name like :nameComponent% order by i.usedCount desc",
            countQuery = "SELECT count(*) FROM Ingredient i WHERE i.name like :nameComponent% "
    )
    Page<IngredientFindResultDTO> findSomeStartWith(@Param("nameComponent")String nameComponent, Pageable pageable);
}

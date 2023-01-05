package com.solution.recipetalk.domain.ingredient.entity;

import com.solution.recipetalk.domain.common.CommonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="ingredient")
public class Ingredient extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ingredient_sort_id", nullable = false)
    private IngredientSort sort;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ingredient_state_id", nullable = false)
    private IngredientState state;

    @Column(nullable = false)
    private Integer calorie;

}

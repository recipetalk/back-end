package com.solution.recipetalk.domain.recipe.ingredient.group.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "recipe_ingredient_group")
public class RecipeIngredientGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
}

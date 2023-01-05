package com.solution.recipetalk.domain.recipe.ingredient.entity;


import com.solution.recipetalk.domain.common.CommonEntity;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.recipe.ingredient.group.entity.RecipeIngredientGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_ingredient_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "quantity")
    private Long quantity;

    @OneToMany(fetch = FetchType.LAZY)
    private List<RecipeIngredientGroup> recipeIngredientGroups;

}



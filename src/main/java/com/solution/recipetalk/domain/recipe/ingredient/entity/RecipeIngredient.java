package com.solution.recipetalk.domain.recipe.ingredient.entity;


import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;




@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_ingredient_id", nullable = false)
    private Long id;

    // ingredient 필드가 있으면 name = null, 없으면 name 존재
    @Column(name = "recipe_ingredient_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "quantity")
    private String quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

}



package com.solution.recipetalk.domain.ingredient.trimming.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name="ingredient_trimming")
public class IngredientTrimming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
}

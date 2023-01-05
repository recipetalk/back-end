package com.solution.recipetalk.domain.recipe.row.entity;

import com.solution.recipetalk.domain.common.CommonEntity;
import com.solution.recipetalk.domain.recipe.row.img.RecipeRowImg;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "recipe_row")
public class RecipeRow extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_row_id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "timer")
    private Long timer; //currentTimeMillis

    @OneToMany(fetch = FetchType.LAZY)
    private List<RecipeRowImg> recipeRowImgs;

}

package com.solution.recipetalk.domain.recipe.row.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.row.img.entity.RecipeRowImg;
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
public class RecipeRow extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_row_id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "timer")
    private Long timer; //currentTimeMillis

    @OneToMany(mappedBy = "recipeRow", fetch = FetchType.LAZY)
    private List<RecipeRowImg> recipeRowImgs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}

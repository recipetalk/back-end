package com.solution.recipetalk.domain.recipe.row.img.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.image.entity.Image;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "recipe_row_img")
public class RecipeRowImg extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_row_img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_row_id", nullable = false)
    private RecipeRow recipeRow;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id",nullable = false)
    private Image image;
}

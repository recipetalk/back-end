package com.solution.recipetalk.domain.recipe.entity;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.common.SoftDeleteEntity;
import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="recipe")
@SQLDelete(sql = "UPDATE recipe SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Recipe extends SoftDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id", nullable = false)
    private Long id;

    @Column(name = "thumbnail_img_uri", nullable = false)
    private String thumbnailImgURI;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    private List<RecipeIngredient> recipeIngredients;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    private List<RecipeRow> recipeRows;
}

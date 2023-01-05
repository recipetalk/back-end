package com.solution.recipetalk.domain.recipe.entity;

import com.solution.recipetalk.domain.common.CommonEntity;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.recipe.ingredient.group.entity.RecipeIngredientGroup;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="recipe")
public class Recipe extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Recipe_id", nullable = false)
    private Long id;

    @Column(name = "thumbnail_img_uri", nullable = false)
    private String thumbnailImgURI;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @OneToMany(fetch = FetchType.LAZY)
    private List<RecipeIngredientGroup> recipeIngredientGroups;

    @OneToMany(fetch = FetchType.LAZY)
    private List<RecipeRow> recipeRows;


}

package com.solution.recipetalk.domain.ingredient.description.entity;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="ingredient_description")
public class IngredientDescription extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_description_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "img_uri")
    private String imgURI;

    @Column(nullable = false)
    private String description;

    public void updateIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void updateImgURI(String imgURI) {
        this.imgURI = imgURI;
    }

    public void updateDescription(String description) {
        this.description = description;
    }
}


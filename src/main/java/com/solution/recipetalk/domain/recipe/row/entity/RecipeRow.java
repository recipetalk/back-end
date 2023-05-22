package com.solution.recipetalk.domain.recipe.row.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


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

    @Column(name = "description", nullable = false)
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(name = "image_uri")
    private String imageURI;

    @Column(name = "seq_num", nullable = false)
    private Long seqNum;

    public void changeDescription(String description){
        if (description != null){
            this.description = description;
        }
    }
}

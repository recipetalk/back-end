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

    @Column(name = "description")
    private String description;

    @Column(name = "timer")
    private Long timer; //currentTimeMillis

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public void changeTimer(Long timer){
        if (timer != null){
            this.timer = timer;
        }
    }

    public void changeDescription(String description){
        if (description != null){
            this.description = description;
        }
    }
}

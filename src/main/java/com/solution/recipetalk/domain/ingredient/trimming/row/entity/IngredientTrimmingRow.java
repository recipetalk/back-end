package com.solution.recipetalk.domain.ingredient.trimming.row.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "ingredient_trimming_row")
public class IngredientTrimmingRow extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_trimming_row_id")
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name = "img_url", nullable = false)
    private String imgURI;
}

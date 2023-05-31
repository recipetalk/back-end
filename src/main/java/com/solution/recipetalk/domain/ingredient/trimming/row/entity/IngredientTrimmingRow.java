package com.solution.recipetalk.domain.ingredient.trimming.row.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
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

    @Column(name = "img_uri")
    private String imgURI;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_trimming_id", nullable = false)
    private IngredientTrimming ingredientTrimming;

    @Column(name = "trimming_seq")
    private Long trimmingSeq;

    public void changeImageURI(String imgURI){
        if (imgURI != null){
            this.imgURI = imgURI;
        }
    }

    public void changeDescription(String description){
        if (description != null){
            this.description = description;
        }
    }

    public void changeTrimmingSeq(Long seqNum){
        this.trimmingSeq = seqNum;
    }
}

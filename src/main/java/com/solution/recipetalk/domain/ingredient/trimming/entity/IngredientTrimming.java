package com.solution.recipetalk.domain.ingredient.trimming.entity;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.common.SoftDeleteEntity;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name="ingredient_trimming")
@SQLDelete(sql = "UPDATE ingredient_trimming SET is_deleted = true WHERE board_id = ?")
@Where(clause = "is_deleted = false")
public class IngredientTrimming extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false ,referencedColumnName = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "thumb_nail_uri")
    private String thumbnailUri;

    @Column(name = "description")
    private String description;

    public void changeThumbnailUri(String thumbnailUri){
        this.thumbnailUri = thumbnailUri;
    }
}

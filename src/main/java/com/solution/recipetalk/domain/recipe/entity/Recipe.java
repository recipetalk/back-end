package com.solution.recipetalk.domain.recipe.entity;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.common.SoftDeleteEntity;
import com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.dto.recipe.RecipeModifyDTO;
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
@SQLDelete(sql = "UPDATE recipe SET is_deleted = true WHERE board_id = ?")
@Where(clause = "is_deleted = false")
public class Recipe extends SoftDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false, referencedColumnName = "board_id")
    private Board board;
    @Column(name = "thumbnail_img_uri")
    private String thumbnailImgURI;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "quantity", nullable = false)
    private RecipeQuantityCategory quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private RecipeLevel level;

    @Enumerated(EnumType.STRING)
    @Column(name = "duration_time", nullable = false)
    private RecipeDurationTime durationTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "sort", nullable = false)
    private RecipeSortCategory sort;

    @Enumerated(EnumType.STRING)
    @Column(name = "situation")
    private RecipeSituationCategory situation;


    // dto 내 null check 를 진행하므로 null check 생략
    public void changeByRecipeModifyDTO(RecipeModifyDTO dto, String thumbnailImgURI){
        this.thumbnailImgURI = thumbnailImgURI;
        this.description = dto.getDescription();
        this.durationTime = dto.getDurationTime();
        this.level = dto.getLevel();
        this.quantity = dto.getQuantity();

        //얘는 널일 수 있음.
        if(dto.getSituation() != null) {
            this.situation = dto.getSituation();
        }

        this.sort = dto.getSort();
    }
}

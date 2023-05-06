package com.solution.recipetalk.domain.ingredient.userhas.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientModifyDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_has_ingredient")
public class UserHasIngredient extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_has_ingredient_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_detail_id", nullable = false)
    private UserDetail user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column
    private IngredientState state;

    @Column
    private String quantity;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public void updateUserHasIngredient(UserHasIngredientModifyDTO dto) {
        if(dto.getState() != null)
            this.state = IngredientState.valueOf(dto.getState());

        if(dto.getQuantity() != null)
            this.quantity = dto.getQuantity();

        if(dto.getExpirationDate() != null) {
            this.expirationDate = dto.getExpirationDate();
        }
    }
}

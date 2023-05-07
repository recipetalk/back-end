package com.solution.recipetalk.domain.ingredient.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="ingredient", indexes = {@Index(name = "ingredient_index", columnList = "ingredient_id")})
public class Ingredient extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

// 주석처리 한 녀석은 추후 넣을 예정
//    @Column(nullable = false)
//    private Integer calorie;

}

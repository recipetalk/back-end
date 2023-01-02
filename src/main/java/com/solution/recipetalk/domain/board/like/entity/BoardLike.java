package com.solution.recipetalk.domain.board.like.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="user_board_liked")
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
}

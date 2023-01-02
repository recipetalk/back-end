package com.solution.recipetalk.domain.user.follow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_follow")
public class UserFollow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
}

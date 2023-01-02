package com.solution.recipetalk.domain.bill.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "bill")
public class Bill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;


}

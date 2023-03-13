package com.solution.recipetalk.domain.verification.token.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification_token", indexes = {
        @Index(name = "idx__token", columnList = "token")})
@SuperBuilder
@NoArgsConstructor
@Getter
public class VerificationToken extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_token_id")
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;

    public void ok(){
        isVerified = true;
    }
}

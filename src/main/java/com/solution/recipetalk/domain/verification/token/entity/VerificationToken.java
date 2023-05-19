package com.solution.recipetalk.domain.verification.token.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @Email
    private String email;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;

    @Enumerated(EnumType.STRING)
    @Column(name = "sort", nullable = false)
    private VerificationSort verificationSort;

    public void updateToken(String token) {
        this.token = token;
    }

    public void updateExpiryDate(LocalDateTime localDateTime){
        this.expiryDate = localDateTime;
    }

    public void ok(){
        isVerified = true;
    }

    public void updateSort(VerificationSort verificationSort) { this.verificationSort = verificationSort;}

    public void denied(){
        isVerified = false;
    }
}

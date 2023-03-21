package com.solution.recipetalk.domain.fcm.entity.temp.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;

@Entity
@Table
@Getter
@SuperBuilder
@NoArgsConstructor
public class TempFcmToken extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temp_fcm_token_id")
    private Long id;

    @Column(name = "fcm_token")
    private String fcmToken;

    @Column(name = "email")
    private String email;

    public void updateFcmToken(String fcmToken) { this.fcmToken = fcmToken; }
}

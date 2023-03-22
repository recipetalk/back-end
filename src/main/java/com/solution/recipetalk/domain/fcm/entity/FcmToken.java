package com.solution.recipetalk.domain.fcm.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "fcm_token")
@SuperBuilder
@NoArgsConstructor
@Getter
public class FcmToken extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fcm_token_id")
    private Long id;

    @Column(name = "fcm_token", nullable = false)
    private String fcmToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_detail_id", nullable = false)
    private UserDetail user;

    @Column(name = "is_listenable", nullable = false)
    private Boolean isListenable;

    public void updateFcmToken(String fcmToken) { this.fcmToken = fcmToken;}

    public void canListen() {
        isListenable = true;
    }

    public void cantListen() {
        isListenable = false;
    }
}

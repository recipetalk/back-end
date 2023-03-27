package com.solution.recipetalk.domain.user.login.password;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "temporary_password")
public class TemporaryPassword extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserLogin userLogin;

    @Column(name = "temp_pw", nullable = false)
    private String temporaryPassword;
}

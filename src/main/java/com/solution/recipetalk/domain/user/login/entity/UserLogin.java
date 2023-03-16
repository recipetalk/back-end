package com.solution.recipetalk.domain.user.login.entity;

import com.solution.recipetalk.domain.common.SoftDeleteEntity;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_login", indexes = {
        @Index(name = "idx__username", columnList = "username", unique = true)
})
@SQLDelete(sql = "UPDATE user_login SET is_deleted = true WHERE id = ?")
public class UserLogin extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserDetail userDetail;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private UserProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType role;

}

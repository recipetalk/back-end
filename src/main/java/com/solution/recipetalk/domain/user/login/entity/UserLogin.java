package com.solution.recipetalk.domain.user.login.entity;

import com.solution.recipetalk.domain.common.CommonEntity;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_login")
public class UserLogin extends CommonEntity {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_detail_id")
    private UserDetail userDetail;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "pw_salt", nullable = false)
    private String pwSalt;
}

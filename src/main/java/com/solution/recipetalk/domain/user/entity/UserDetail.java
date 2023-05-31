package com.solution.recipetalk.domain.user.entity;

import com.solution.recipetalk.domain.common.SoftDeleteEntity;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_detail", indexes = {@Index(name = "idx_username", columnList = "username", unique = true)})
@SQLDelete(sql = "UPDATE user_detail SET is_deleted = true WHERE id = ?")
public class UserDetail extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_detail_id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "profile_image_uri", nullable = false)
    private String profileImageURI;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "userDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserLogin userLogin;

    @Column(name = "is_blocked")
    private Boolean isBlocked;
    //TODO : Where 절 없는 이유? 관리 입장에서 없어야 할 수 있음. 따라서 직접 false이면 어떻게, true이면 어떻게 조회해야 할지에 대한 처리 필요.

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public void setNickname(String nickname){
        if (null != nickname)
            this.nickname = nickname;
    }

    public void setProfileImageURI(String profileImageURI){
        if(null != profileImageURI)
            this.profileImageURI = profileImageURI;
    }

    public void setDescription(String description){
        if(null != description)
            this.description = description;
    }

}

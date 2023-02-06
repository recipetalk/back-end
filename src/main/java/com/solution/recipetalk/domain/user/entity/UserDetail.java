package com.solution.recipetalk.domain.user.entity;

import com.solution.recipetalk.domain.common.SoftDeleteEntity;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_detail")
@SQLDelete(sql = "UPDATE user_detail SET is_deleted = true WHERE id = ?")
public class UserDetail extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_detail_id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "profile_image_uri", nullable = false)
    private String profileImageURI;

    @Column(name = "phone_num", nullable = false)
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})")
    private String phoneNum;

    @OneToOne(mappedBy = "userDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserLogin userLogin;

    //TODO : Where 절 없는 이유? 관리 입장에서 없어야 할 수 있음. 따라서 직접 false이면 어떻게, true이면 어떻게 조회해야 할지에 대한 처리 필요.

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}

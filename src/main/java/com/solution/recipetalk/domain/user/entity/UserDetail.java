package com.solution.recipetalk.domain.user.entity;

import com.solution.recipetalk.domain.common.CommonEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_detail")
public class UserDetail extends CommonEntity {
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
}

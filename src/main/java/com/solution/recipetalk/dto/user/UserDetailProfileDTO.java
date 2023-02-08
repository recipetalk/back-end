package com.solution.recipetalk.dto.user;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailProfileDTO {
    private String nickname;
    private String username;
    private String description;
    private String profileImg;
    private Long followNum;

    public static UserDetailProfileDTO toDTO(UserLogin userLogin, UserDetail userDetail, Long followNum){
        return UserDetailProfileDTO.builder()
                .description(userDetail.getDescription())
                .nickname(userDetail.getNickname())
                .profileImg(userDetail.getProfileImageURI())
                .followNum(followNum)
                .username(userLogin.getUsername())
                .build();
    }
}

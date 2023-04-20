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
    private Long followingNum;
    private Long followerNum;
    private Long recipeNum;

    public static UserDetailProfileDTO toDTO(UserDetail userDetail, Long followingNum, Long followerNum, Long recipeNum){
        return UserDetailProfileDTO.builder()
                .description(userDetail.getDescription())
                .nickname(userDetail.getNickname())
                .profileImg(userDetail.getProfileImageURI())
                .followingNum(followingNum)
                .followerNum(followerNum)
                .recipeNum(recipeNum)
                .username(userDetail.getUsername())
                .build();
    }
}

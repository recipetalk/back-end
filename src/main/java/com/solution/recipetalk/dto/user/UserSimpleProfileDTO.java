package com.solution.recipetalk.dto.user;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserSimpleProfileDTO {
    private String username;
    private String nickname;
    private String profileImageURI;

    public UserSimpleProfileDTO(String username, String nickname, String profileImageURI){
        this.username = username;
        this.nickname = nickname;
        this.profileImageURI = profileImageURI;
    }

    public static UserSimpleProfileDTO toDTO(UserDetail userDetail) {
        return UserSimpleProfileDTO.builder()
                .nickname(userDetail.getNickname())
                .username(userDetail.getUsername())
                .profileImageURI(userDetail.getProfileImageURI())
                .build();
    }
}

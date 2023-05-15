package com.solution.recipetalk.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSimpleProfileDTO {
    private String username;
    private String nickname;
    private String profileImageURI;
    private String description;
    private Boolean isFollowing;

    public UserSimpleProfileDTO(String username, String nickname, String profileImageURI){
        this.username = username;
        this.nickname = nickname;
        this.profileImageURI = profileImageURI;
    }

    public UserSimpleProfileDTO(String username, String nickname, String profileImageURI, String description){
        this.username = username;
        this.nickname = nickname;
        this.profileImageURI = profileImageURI;
        this.description = description;
    }

    public UserSimpleProfileDTO(String username, String nickname, String profileImageURI, Boolean isFollowing) {
        this(username,nickname, profileImageURI);
        this.isFollowing = isFollowing;
    }

    public static UserSimpleProfileDTO toDTO(UserDetail userDetail) {
        return UserSimpleProfileDTO.builder()
                .nickname(userDetail.getNickname())
                .username(userDetail.getUsername())
                .profileImageURI(userDetail.getProfileImageURI())
                .build();
    }
    public static UserSimpleProfileDTO toDTO(UserDetail userDetail, Boolean isFollowing) {
        return UserSimpleProfileDTO.builder()
                .nickname(userDetail.getNickname())
                .username(userDetail.getUsername())
                .profileImageURI(userDetail.getProfileImageURI())
                .description(userDetail.getDescription())
                .isFollowing(isFollowing)
                .build();
    }
}

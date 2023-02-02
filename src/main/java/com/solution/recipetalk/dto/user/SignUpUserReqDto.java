package com.solution.recipetalk.dto.user;

import com.solution.recipetalk.domain.user.login.entity.RoleType;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.entity.UserProvider;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpUserReqDto {
    private String nickname;
    private String phoneNum;
    private String username;
    private String password;

    public UserDetail toUserDetail(){
        return UserDetail.builder()
                .nickname(nickname)
                .phoneNum(phoneNum)
                .profileImageURI("")
                .build();
    }

    public UserLogin toUserLogin(UserDetail userDetail, String encryptedPassword){
        return UserLogin.builder()
                .userDetail(userDetail)
                .password(encryptedPassword)
                .username(username)
                .provider(UserProvider.NONE)
                .role(RoleType.USER)
                .build();
    }
}

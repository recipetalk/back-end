package com.solution.recipetalk.dto.user;

import com.solution.recipetalk.domain.user.login.entity.RoleType;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.entity.UserProvider;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpUserReqDto {
    @NonNull
    @Pattern(regexp = "[ㄱ-ㅎ가-힣a-zA-Z0-9]{3,10}$")
    private String nickname;
    @NonNull
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;
    @NonNull
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$")
    private String username;
    @NonNull
    private String password;

    public UserDetail toUserDetail(){
        return UserDetail.builder()
                .nickname(nickname)
                .profileImageURI("")
                .username(username)
                .isBlocked(false)
                .build();
    }

    public UserLogin toUserLogin(UserDetail userDetail, String encryptedPassword){
        return UserLogin.builder()
                .userDetail(userDetail)
                .password(encryptedPassword)
                .username(username)
                .email(email)
                .provider(UserProvider.NONE)
                .role(RoleType.USER)
                .build();
    }
}

package com.solution.recipetalk.security.security.service;

import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class UserLoginContext extends User {

    private final UserLogin userLogin;

    public UserLoginContext(UserLogin userLogin, Collection<? extends GrantedAuthority> authorities) {
        super(userLogin.getUsername(), userLogin.getPassword(), authorities);
        this.userLogin = userLogin;

    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

}




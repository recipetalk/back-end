package com.solution.recipetalk.util;

import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import org.springframework.security.core.context.SecurityContextHolder;


//TODO : Test 필요
public class ContextHolder {

    public static UserLogin getUserLogin(){
        return (UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
    public static Long getUserLoginId() {
        return getUserLogin().getId();
    }
}

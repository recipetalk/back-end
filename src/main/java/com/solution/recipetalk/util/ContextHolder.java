package com.solution.recipetalk.util;

import com.solution.recipetalk.config.auth.PrincipalDetails;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;



//TODO : Test 필요
public class ContextHolder {

    public static UserDetail getUserDetail(){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return principalDetails.getUser().getUserDetail();
    }
}

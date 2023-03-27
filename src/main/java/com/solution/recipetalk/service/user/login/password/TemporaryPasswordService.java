package com.solution.recipetalk.service.user.login.password;

import com.solution.recipetalk.domain.user.login.entity.UserLogin;

public interface TemporaryPasswordService {
    String createTemporaryPassword(UserLogin userLogin);
}

package com.solution.recipetalk.domain.user.login.password.repository;

import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.password.TemporaryPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryPasswordRepository extends JpaRepository<TemporaryPassword, UserLogin> {
    Optional<TemporaryPassword> findByUserLogin(UserLogin id);
}

package com.solution.recipetalk.domain.user.repository;

import com.solution.recipetalk.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.solution.recipetalk.domain.user.phone.repository;

import com.solution.recipetalk.domain.user.phone.entity.PhoneAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneAuthenticationRepository extends JpaRepository<PhoneAuthentication, String> {

}

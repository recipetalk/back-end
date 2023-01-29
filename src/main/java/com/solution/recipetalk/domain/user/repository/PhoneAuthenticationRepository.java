package com.solution.recipetalk.domain.user.repository;

import com.solution.recipetalk.domain.user.phone.PhoneAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneAuthenticationRepository extends JpaRepository<PhoneAuthentication, String> {

}

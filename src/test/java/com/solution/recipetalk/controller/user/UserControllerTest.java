package com.solution.recipetalk.controller.user;

import com.solution.recipetalk.domain.user.entity.RoleType;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.entity.UserProvider;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private MockMvc mvc;

    @BeforeAll
    void setUserDetailAndUserLogin() {
        UserDetail userDetail = UserDetail.builder().id(1L).nickname("test1").phoneNum("01031798788").provider(UserProvider.GOOGLE).role(RoleType.DEV).profileImageURI("testURI").build();
        userDetailRepository.save(userDetail);

        UserLogin userLogin = UserLogin.builder().userDetail(userDetail).password("test1").pwSalt("11").username("test").build();
        userDetail.setUserLogin(userLogin);
        userDetailRepository.save(userDetail);
    }

    @Test
    @DisplayName("[USER][GET] 아이디 사용 가능 확인")
    void duplicatedUserCheckIsFalse() throws Exception {
        Optional<UserLogin> userLogin = userLoginRepository.findByUsername("test");
        mvc.perform(get("/user/signup/test")
            )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isValid").value("false"));
    }


    @Test
    @DisplayName("[USER][GET] 아이디 사용 불가 확인")
    void duplicatedUserCheckIsTrue() throws Exception {
        Optional<UserLogin> userLogin = userLoginRepository.findByUsername("test1");
        mvc.perform(get("/user/signup/test1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isValid").value("true"));
    }
}
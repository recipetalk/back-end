package com.solution.recipetalk.service.sms.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;


@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
class SensApiServiceImplTest {
    @Autowired
    private SensApiServiceImpl sensApiService;

    @Test
    @DisplayName("[USER]휴대폰인증번호 전송 테스트")
    public void sendSMS(){
        String testNum = "01031798788";

        System.out.println(sensApiService.sendSMS(testNum));
    }
}
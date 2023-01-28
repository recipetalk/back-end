package com.solution.recipetalk.service.sms;

import org.springframework.http.ResponseEntity;

public interface SMSRequestService {
    ResponseEntity<?> sendSMS(String phoneNum);
}

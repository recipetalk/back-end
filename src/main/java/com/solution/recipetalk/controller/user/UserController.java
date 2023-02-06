package com.solution.recipetalk.controller.user;


import com.solution.recipetalk.service.sms.SMSRequestService;
import com.solution.recipetalk.service.user.FindUserService;
import com.solution.recipetalk.service.user.RegisterUserService;
import com.solution.recipetalk.service.user.VerifyAuthenticationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final FindUserService findUserService;

    @Autowired
    private final RegisterUserService registerUserService;

    @Autowired
    private final SMSRequestService smsRequestService;

    @Autowired
    private final VerifyAuthenticationService verifyAuthenticationService;




}

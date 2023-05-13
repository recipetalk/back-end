package com.solution.recipetalk.config.auth;

import com.solution.recipetalk.service.verification.token.VerificationTokenService;
import com.solution.recipetalk.service.verification.user.UserVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class VerifyController {

    private final VerificationTokenService verificationTokenService;
    private final UserVerificationService userVerificationService;

    @GetMapping("/verify")
    public String tokenVerify(@RequestParam String token){

        return verificationTokenService.verifyToken(token);
    }

    @GetMapping("/verified")
    @ResponseBody
    public String emailVerified() {
        return "이메일 인증 성공";
    }

    @GetMapping("/verifyFailed")
    @ResponseBody
    public String emailVerifyFailed() {
        return "이메일 인증 실패, 이메일을 다시 한번 요청해주세요";
    }

    @GetMapping("/verify/user")
    public String userVerify(@RequestParam(name = "user") String username, @RequestParam(name = "token") String token) {
        return userVerificationService.verifyUser(username, token);
    }
}

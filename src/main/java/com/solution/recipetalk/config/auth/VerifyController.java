package com.solution.recipetalk.config.auth;

import com.solution.recipetalk.domain.verification.token.entity.VerificationSort;
import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
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
    public String tokenVerify(@RequestParam String token, @RequestParam("type") VerificationSort sort){

        return verificationTokenService.verifyToken(token, sort);
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

}

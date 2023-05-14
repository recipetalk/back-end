package com.solution.recipetalk.config.auth;

import com.solution.recipetalk.domain.verification.token.type.VerificationType;
import com.solution.recipetalk.service.verification.token.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class VerifyController {

    private final VerificationTokenService verificationTokenService;

    @GetMapping("/verify")
    public String tokenVerify(@RequestParam String token, @RequestParam VerificationType type){

        return verificationTokenService.verifyToken(token, type);
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

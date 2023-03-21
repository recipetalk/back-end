package com.solution.recipetalk.config.auth;


import com.solution.recipetalk.dto.user.PhoneAuthRequestDTO;
import com.solution.recipetalk.dto.user.PhoneAuthVerificationRequestDTO;
import com.solution.recipetalk.dto.user.SignUpUserReqDto;
import com.solution.recipetalk.service.mail.SendMailForTemporaryPasswordService;
import com.solution.recipetalk.service.mail.SendMailService;
import com.solution.recipetalk.service.sms.SMSRequestService;
import com.solution.recipetalk.service.user.FindUserService;
import com.solution.recipetalk.service.user.RegisterUserService;
import com.solution.recipetalk.service.user.VerifyAuthenticationService;
import com.solution.recipetalk.service.user.login.FindUserLoginService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final FindUserService findUserService;

    @Autowired
    private final RegisterUserService registerUserService;

    @Autowired
    private final SMSRequestService smsRequestService;

    @Autowired
    private final VerifyAuthenticationService verifyAuthenticationService;

    private final FindUserLoginService findUserLoginService;

    private final SendMailService sendMailService;

    private final SendMailForTemporaryPasswordService sendMailForTemporaryPasswordService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody @NonNull SignUpUserReqDto signUpUserReqDto){
        return registerUserService.addUser(signUpUserReqDto);
    }

    @PostMapping("/signup/phone")
    public ResponseEntity<?> sendPhoneAuthSMS(@RequestBody @Valid @NonNull PhoneAuthRequestDTO dto) {
        return smsRequestService.sendSMS(dto.getPhoneNum());
    }

    @PatchMapping("/signup/phone")
    public ResponseEntity<?> verifyPhoneAuth(@RequestBody @Valid @NonNull PhoneAuthVerificationRequestDTO dto) {
        return verifyAuthenticationService.verifyAuthentication(dto.getPhoneNum(), dto.getAuthNum());
    }

    @GetMapping("/signup/{id}")
    public ResponseEntity<?> duplicatedUserCheck(@PathVariable("id") @NonNull String userName) {
        return findUserService.findDuplicatedUsernameInUserLogin(userName);
    }

    @GetMapping("/signup/nickname/{nickname}")
    public ResponseEntity<?> duplicatedNicknameCheck(@PathVariable(name = "nickname") @NonNull String nickname) {
        return findUserService.findDuplicatedNicknameInUserLogin(nickname);
    }

    @GetMapping("/signup/email/{email}")
    public ResponseEntity<?> duplicatedEmailCheck(@PathVariable(name = "email") @NonNull String email) {
        return findUserLoginService.findDuplicatedEmailInUserLogin(email);
    }

    @GetMapping("/verify/{email}")
    public ResponseEntity<?> tokenRequest(@PathVariable(name = "email")String email){
        return sendMailService.sendEmail(email);
    }

    @GetMapping("/find/id/{email}")
    public ResponseEntity<?> forgottenUsernameDetailByEmailAddress(@PathVariable(name = "email") String email) {
        return findUserLoginService.findUsernameByEmailAddress(email);
    }

    @GetMapping("/find/password/{username}")
    public ResponseEntity<?> forgottenPasswordModify(@PathVariable(name = "username") @NonNull String username) {
        // 아이디, 즉 username을 PathVariable로 받고 그걸 사용해서 userLogin 정보를 찾은 뒤,
        // 거기 담긴 이메일 주소로 임시 비밀번호를 만들어 발송한 뒤, 해당 userLogin의 비밀번호도 수정한다.
        // 나중에 사용자가 임시 비번을 다시 바꿔놔야 함
        return sendMailForTemporaryPasswordService.sendEmail(username);
    }
}

package com.solution.recipetalk.config.auth;


import com.solution.recipetalk.dto.fcm.temp.TempFcmTokenRegisterDTO;
import com.solution.recipetalk.dto.user.PhoneAuthRequestDTO;
import com.solution.recipetalk.dto.user.PhoneAuthVerificationRequestDTO;
import com.solution.recipetalk.dto.user.SignUpUserReqDto;
import com.solution.recipetalk.service.fcm.temp.RegisterTempFcmTokenService;
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

    private final RegisterTempFcmTokenService registerTempFcmTokenService;


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

    @PostMapping("/connect")
    public ResponseEntity<?> tempFcmTokenAdd(@RequestBody TempFcmTokenRegisterDTO dto){
        return registerTempFcmTokenService.registerTempFcmTokenService(dto);
    }

}

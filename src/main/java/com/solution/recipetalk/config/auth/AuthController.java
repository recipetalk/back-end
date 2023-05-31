package com.solution.recipetalk.config.auth;


import com.solution.recipetalk.dto.fcm.temp.TempFcmTokenRegisterDTO;
import com.solution.recipetalk.dto.user.*;
import com.solution.recipetalk.service.fcm.temp.RegisterTempFcmTokenService;
import com.solution.recipetalk.service.mail.SendMailForModifyingPasswordService;
import com.solution.recipetalk.service.mail.SendMailService;
import com.solution.recipetalk.service.sms.SMSRequestService;
import com.solution.recipetalk.service.user.FindUserService;
import com.solution.recipetalk.service.user.RegisterUserService;
import com.solution.recipetalk.service.user.VerifyAuthenticationService;
import com.solution.recipetalk.service.user.login.FindUserLoginService;
import com.solution.recipetalk.service.user.login.ModifyUserLoginService;
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

    private final ModifyUserLoginService modifyUserLoginService;

    private final SendMailService sendMailService;

    private final RegisterTempFcmTokenService registerTempFcmTokenService;

    private final SendMailForModifyingPasswordService sendMailForModifyingPasswordService;

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

    @PostMapping("/find/pw")
    public ResponseEntity<?> forgottenPasswordModify(@RequestBody ForgottenPasswordFindResponseDTO dto) {
        return sendMailForModifyingPasswordService.sendEmail(dto);
    }

    @PatchMapping("/reset/pw")
    public ResponseEntity<?> passwordReset(@RequestBody PasswordResetDTO dto) {
        return modifyUserLoginService.modifyPassword(dto);
    }
}

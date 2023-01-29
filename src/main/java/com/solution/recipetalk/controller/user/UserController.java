package com.solution.recipetalk.controller.user;


import com.solution.recipetalk.dto.user.PhoneAuthRequestDTO;
import com.solution.recipetalk.dto.user.PhoneAuthVerificationRequestDTO;
import com.solution.recipetalk.service.sms.SMSRequestService;
import com.solution.recipetalk.config.jwt.JwtTokenHeader;
import com.solution.recipetalk.config.jwt.token.RequestToken;
import com.solution.recipetalk.config.jwt.token.properties.AccessTokenProperties;
import com.solution.recipetalk.config.jwt.token.properties.RefreshTokenProperties;
import com.solution.recipetalk.dto.user.SignUpUserReqDto;
import com.solution.recipetalk.service.user.FindUserService;
import com.solution.recipetalk.service.user.RegisterUserService;
import jakarta.servlet.http.HttpServletRequest;
import com.solution.recipetalk.service.user.VerifyAuthenticationService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final FindUserService findUserService;

    @Autowired
    private final RegisterUserService registerUserService;

    @Autowired
    private final SMSRequestService smsRequestService;

    @Autowired
    private final VerifyAuthenticationService verifyAuthenticationService;

    @GetMapping("/signup/{id}")
    public ResponseEntity<?> duplicatedUserCheck(@PathVariable("id") @NonNull String userName) {
        return findUserService.findDuplicatedUsernameInUserLogin(userName);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpUserReqDto signUpUserReqDto){
        return registerUserService.addUser(signUpUserReqDto);
    }

    @GetMapping("/need/auth")
    public ResponseEntity<?> needAuthTest(HttpServletRequest request){
        String username = getUsername(request);
        return new ResponseEntity<>("권한 인증 완료 (" + username + ")", HttpStatus.OK);
    }

    private String getUsername(HttpServletRequest request){
        JwtTokenHeader jwtAccessTokenHeader = new JwtTokenHeader(AccessTokenProperties.HEADER_STRING, request);
        JwtTokenHeader jwtRefreshTokenHeader = new JwtTokenHeader(RefreshTokenProperties.HEADER_STRING, request);
        try{
            RequestToken requestToken = new RequestToken(jwtAccessTokenHeader);
            return requestToken.getElementInToken(requestToken.getToken(), "username");
        }catch (Exception e){
            if (jwtRefreshTokenHeader.getHeaderData() != null){
                RequestToken requestToken = new RequestToken(jwtRefreshTokenHeader);
                return requestToken.getElementInToken(requestToken.getToken(), "username");
            }
            throw new RuntimeException("권한없음");
        }
    }

    @PostMapping("/signup/phone")
    public ResponseEntity<?> sendPhoneAuthSMS(@RequestBody @Valid @NonNull PhoneAuthRequestDTO dto) {
        return smsRequestService.sendSMS(dto.getPhoneNum());
    }

    @PatchMapping("/signup/phone")
    public ResponseEntity<?> verifyPHoneAuth(@RequestBody @Valid @NonNull PhoneAuthVerificationRequestDTO dto) {
        return verifyAuthenticationService.verifyAuthentication(dto.getPhoneNum(), dto.getAuthNum());
    }
}

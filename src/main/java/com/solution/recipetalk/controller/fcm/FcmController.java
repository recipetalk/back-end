package com.solution.recipetalk.controller.fcm;

import com.solution.recipetalk.dto.fcm.FcmTokenRegisterDTO;
import com.solution.recipetalk.service.fcm.RegisterFcmTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class FcmController {
    private final RegisterFcmTokenService registerFcmTokenService;


    @PostMapping("/connect")
    public ResponseEntity<?> fcmTokenAdd(@ModelAttribute FcmTokenRegisterDTO dto){
        return registerFcmTokenService.registerFcmToken(dto);
    }

}

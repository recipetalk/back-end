package com.solution.recipetalk.controller.fcm;


import com.solution.recipetalk.dto.fcm.FcmTokenDTO;
import com.solution.recipetalk.service.fcm.ModifyFcmTokenService;
import com.solution.recipetalk.service.fcm.RegisterFcmTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class FcmController {
    private final RegisterFcmTokenService registerFcmTokenService;
    private final ModifyFcmTokenService modifyFcmTokenService;

    @PostMapping("/connect")
    public ResponseEntity<?> fcmTokenAdd(@ModelAttribute FcmTokenDTO dto){
        return registerFcmTokenService.registerFcmToken(dto);
    }

    @PatchMapping("/notification")
    public ResponseEntity<?> fcmTokenModify(FcmTokenDTO dto){
        return modifyFcmTokenService.modifyFcmToken(dto);
    }

}

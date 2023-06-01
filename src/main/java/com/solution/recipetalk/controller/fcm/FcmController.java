package com.solution.recipetalk.controller.fcm;

import com.solution.recipetalk.dto.fcm.FcmTokenDTO;
import com.solution.recipetalk.service.fcm.ModifyFcmTokenService;
import com.solution.recipetalk.service.fcm.RegisterFcmTokenService;
import com.solution.recipetalk.service.fcm.RemoveFcmTokenService;
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
    private final RemoveFcmTokenService removeFcmTokenService;

    @PostMapping("/connect")
    public ResponseEntity<?> fcmTokenAdd(@RequestBody FcmTokenDTO dto){
        return registerFcmTokenService.registerFcmToken(dto);
    }

    @PatchMapping("/connect")
    public ResponseEntity<?> fcmTokenModify(FcmTokenDTO dto){
        return modifyFcmTokenService.modifyFcmToken(dto);
    }

    @DeleteMapping("/connect")
    public ResponseEntity<?> fcmTokenRemove(){
        return removeFcmTokenService.removeFcmToken();
    }
}

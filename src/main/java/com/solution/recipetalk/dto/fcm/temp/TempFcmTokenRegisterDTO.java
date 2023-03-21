package com.solution.recipetalk.dto.fcm.temp;

import com.solution.recipetalk.domain.fcm.entity.temp.entity.TempFcmToken;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TempFcmTokenRegisterDTO {
    private String email;
    private String fcmToken;


    public TempFcmToken toEntity() {
        return TempFcmToken.builder().fcmToken(fcmToken).email(email).build();
    }
}

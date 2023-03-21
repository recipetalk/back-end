package com.solution.recipetalk.dto.fcm;

import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FcmTokenRegisterDTO {
    private String fcmToken;

    public FcmToken toEntity(UserDetail user){
        return FcmToken.builder()
                .user(user)
                .fcmToken(fcmToken)
                .build();
    }
}

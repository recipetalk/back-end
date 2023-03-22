package com.solution.recipetalk.dto.fcm;

import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FcmTokenDTO {

    @NonNull
    private String fcmToken;

    @NonNull
    private Boolean isListenable;

    public FcmToken toEntity(UserDetail user){
        return FcmToken.builder()
                .user(user)
                .fcmToken(fcmToken)
                .isListenable(isListenable)
                .build();
    }
}

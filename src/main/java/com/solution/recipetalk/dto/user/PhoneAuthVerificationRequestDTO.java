package com.solution.recipetalk.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PhoneAuthVerificationRequestDTO {
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "휴대폰 형식이 맞지 않습니다.")
    @NonNull
    private String phoneNum;

    @Pattern(regexp = "\\d{6}", message = "인증번호 형식이 맞지 않습니다.") // 무작위 숫자 6개 아무거나 다.
    @NonNull
    private String authNum;


}

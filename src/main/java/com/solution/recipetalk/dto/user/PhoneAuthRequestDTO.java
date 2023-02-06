package com.solution.recipetalk.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneAuthRequestDTO {
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "휴대폰 형식이 맞지 않습니다.")
    @NonNull
    private String phoneNum;
}

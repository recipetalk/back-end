package com.solution.recipetalk.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PhoneAuthenticationResponseDTO {
    private LocalDateTime requestTime;
    private String statusName;
}

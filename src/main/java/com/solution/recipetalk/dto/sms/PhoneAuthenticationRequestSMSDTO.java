package com.solution.recipetalk.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PhoneAuthenticationRequestSMSDTO {
    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    private List<MessageDTO> messages;
}
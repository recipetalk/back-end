package com.solution.recipetalk.domain.sms;


import com.solution.recipetalk.dto.sms.MessageDTO;
import com.solution.recipetalk.dto.sms.PhoneAuthenticationRequestSMSDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SMS {

    private final static String SMS_TYPE = "SMS";
    private final static String CONTENT_TYPE = "COMM";
    private final static String COUNTRY_CODE = "82"; //대한민국



    public PhoneAuthenticationRequestSMSDTO toDTO(String content, String fromPhoneNum, String toPhoneNum) {
        ArrayList<MessageDTO> messages = new ArrayList<>();
        messages.add(MessageDTO.builder().to(toPhoneNum).build());

        return PhoneAuthenticationRequestSMSDTO.builder().type(SMS_TYPE)
                .contentType(CONTENT_TYPE)
                .countryCode(COUNTRY_CODE)
                .from(fromPhoneNum)
                .content(content)
                .messages(messages)
                .build();
    }
}

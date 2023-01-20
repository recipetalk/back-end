package com.solution.recipetalk.domain.sms;

import com.solution.recipetalk.dto.sms.PhoneAuthenticationRequestSMSDTO;
import org.springframework.stereotype.Component;

@Component
public class PhoneAuthentication extends SMS{
    private final static String CONTENT_FORMAT = "[레시피톡] 휴대폰 인증번호는 %s 입니다. 본인이 아닐 시 문의 바랍니다.";

    public PhoneAuthenticationRequestSMSDTO toDTO(String fromPhoneNum, String toPhoneNum, String randNum){
        return super.toDTO(String.format(CONTENT_FORMAT, randNum), fromPhoneNum, toPhoneNum);
    }

}

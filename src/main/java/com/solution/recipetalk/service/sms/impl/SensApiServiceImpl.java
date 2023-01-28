package com.solution.recipetalk.service.sms.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.recipetalk.domain.sms.PhoneAuthenticationSMS;
import com.solution.recipetalk.dto.sms.PhoneAuthenticationRequestSMSDTO;
import com.solution.recipetalk.exception.signup.AuthRequestTimeoutException;
import com.solution.recipetalk.service.sms.SMSRequestService;
import com.solution.recipetalk.util.RandomNumberProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class SensApiServiceImpl implements SMSRequestService {

    @Autowired
    private final PhoneAuthenticationSMS phoneAuthentication;
    @Value("${open-api.naver-sms.service-id}") private final String serviceId;
    @Value("${open-api.naver-sms.access-key}") private final String accessKey;
    @Value("${open-api.naver-sms.secret-key}") private final String secretKey;
    @Value("${open-api.naver-sms.sender-phone-number}") private final String fromPhoneNum;

    @Override
    public ResponseEntity<?> sendSMS(String toPhoneNum) {
        final String origin = "https://sens.apigw.ntruss.com"; // Naver SENS
        final String uri = "/sms/v2/services/" + serviceId + "/messages";

        final String currentTime = Long.toString(System.currentTimeMillis());

        String randNum = RandomNumberProvider.getRandNum(6);

        //TODO : 인증번호 저장 확인해야 함. 인증 시간도 같이 포함하도록 하고, 당일 최대 인증 가능 횟수는 휴대폰 번호당 5회로 지정하도록.


        PhoneAuthenticationRequestSMSDTO dto = phoneAuthentication.toDTO(fromPhoneNum, toPhoneNum ,randNum);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-ncp-apigw-timestamp", currentTime);
            headers.set("x-ncp-iam-access-key", accessKey);

            String signature = makeSignature(uri, currentTime);
            headers.set("x-ncp-apigw-signature-v2", signature);    // 시그니처 서명

            // SMSRequestDTO to json body
            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writeValueAsString(dto);

            // header + body
            HttpEntity<String> request = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<?> response = restTemplate.exchange(new URI(origin + uri), HttpMethod.POST, request, String.class);
            return ResponseEntity.status(response.getStatusCode()).build();

        } catch (URISyntaxException | JsonProcessingException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("SMS 발송 준비 중 예외 발생", e);
            throw new AuthRequestTimeoutException();

        }
    }

    private String makeSignature(String uri, String timestamp) throws NoSuchAlgorithmException, InvalidKeyException {
        final String space = " ";					// one space
        final String newLine = "\n";					// new line
        final String method = "POST";

        String message = method + space + uri +
                newLine +
                timestamp +
                newLine +
                accessKey;

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeBase64String(rawHmac);
    }

}

package com.solution.recipetalk.service.mail.impl;

import com.solution.recipetalk.domain.fcm.entity.temp.entity.TempFcmToken;
import com.solution.recipetalk.domain.fcm.entity.temp.repository.TempFcmTokenRepository;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
import com.solution.recipetalk.dto.user.ForgottenPasswordFindResponseDTO;
import com.solution.recipetalk.exception.user.UserInformationMatchException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.mail.SendMailForModifyingPasswordService;
import com.solution.recipetalk.service.verification.token.VerificationTokenService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@Transactional
@RequiredArgsConstructor
public class SendMailForModifyingPasswordServiceImpl implements SendMailForModifyingPasswordService {
    private final JavaMailSender emailSender;

    private final SpringTemplateEngine templateEngine;

    private final VerificationTokenService verificationTokenService;

    private final TempFcmTokenRepository tempFcmTokenRepository;



    private final UserLoginRepository userLoginRepository;

    @Value("[레시피톡] 비밀번호를 변경해주세요.") private final String title;
    @Value("${spring.mail.username}") private final String username;
    @Value("${spring.host}") private final String host;

    @Override
    public ResponseEntity<?> sendEmail(ForgottenPasswordFindResponseDTO dto) {
        try {
            MimeMessage emailForm = createEmailForm(dto);
            emailSender.send(emailForm);
        } catch(MessagingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private MimeMessage createEmailForm(ForgottenPasswordFindResponseDTO dto) throws MessagingException {
        UserLogin byUsername = userLoginRepository.findByUsername(dto.getUsername()).orElseThrow(UserNotFoundException::new);

        if(!byUsername.getEmail().equals(dto.getEmail())) {
            throw new UserNotFoundException();
        }

        TempFcmToken fcmToken = TempFcmToken.builder().email(dto.getEmail()).fcmToken(dto.getFcmToken()).build();

        tempFcmTokenRepository.save(fcmToken);

        VerificationToken token = verificationTokenService.createVerificationToken(byUsername.getEmail());

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, byUsername.getEmail());
        message.setSubject(title);
        message.setFrom(this.username + "@naver.com");
        message.setText(setContext(dto.getUsername(), token.getToken()), "utf-8", "html");

        return message;
    }

    private String setContext(String username, String token) {
        Context context = new Context();
        context.setVariable("password", host + "auth/verify/user?user=" + username + "&token=" + token);

        return templateEngine.process("tmpPwMail", context);
    }
}

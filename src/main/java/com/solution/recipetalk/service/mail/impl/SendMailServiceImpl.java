package com.solution.recipetalk.service.mail.impl;

import com.solution.recipetalk.domain.verification.token.entity.VerificationSort;
import com.solution.recipetalk.domain.verification.token.entity.VerificationToken;
import com.solution.recipetalk.service.mail.SendMailService;
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
public class SendMailServiceImpl implements SendMailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    private final VerificationTokenService verificationTokenService;

    @Value("${spring.mail.title}") private final String title;
    @Value("${spring.mail.username}") private final String username;
    @Value("${spring.host}") private final String host;
    @Override
    public ResponseEntity<?> sendEmail(String toEmail) {
        try {
            MimeMessage emailForm = createEmailForm(toEmail);
            emailSender.send(emailForm);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    private MimeMessage createEmailForm(String toEmail) throws MessagingException {
        //인증 토큰 생성
        VerificationToken verificationToken = verificationTokenService.createVerificationToken(toEmail, VerificationSort.E_MAIL);
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, toEmail);
        message.setSubject(title);
        message.setFrom(username+"@naver.com");
        message.setText(setContext(verificationToken.getToken(), VerificationSort.E_MAIL), "utf-8", "html");

        return message;
    }

    private String setContext(String verificationToken, VerificationSort sort){
        Context context = new Context();
        context.setVariable("token", host+"auth/verify?token="+verificationToken+"&type=" + sort.name());
        return templateEngine.process("mail", context);
    }

}

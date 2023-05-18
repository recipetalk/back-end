package com.solution.recipetalk.service.mail;

import com.solution.recipetalk.dto.user.ForgottenPasswordFindResponseDTO;
import org.springframework.http.ResponseEntity;

public interface SendMailForModifyingPasswordService {
    ResponseEntity<?> sendEmail(ForgottenPasswordFindResponseDTO dto);
}

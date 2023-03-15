package com.solution.recipetalk.service.user.block;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FindUserBlockService {

    ResponseEntity<?> findUserBlockUserList(Pageable pageable);
}

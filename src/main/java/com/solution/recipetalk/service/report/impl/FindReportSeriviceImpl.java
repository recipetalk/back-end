package com.solution.recipetalk.service.report.impl;

import com.solution.recipetalk.domain.report.entity.Report;
import com.solution.recipetalk.domain.report.repository.ReportRepository;
import com.solution.recipetalk.domain.user.login.entity.RoleType;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.report.ReportResponseDTO;
import com.solution.recipetalk.dto.report.ReportSimpleResponseDTO;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.report.ReportNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.report.FindReportService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class FindReportSeriviceImpl implements FindReportService {
    private final ReportRepository reportRepository;
    private final UserDetailRepository userLoginRepository;

    @Override
    public ResponseEntity<?> findAllReportsByReporter(Pageable pageable) {

        validateCurrentUserIsAdmin();

        Page<ReportResponseDTO> responseDTOS = reportRepository.findAllReports(pageable);

        return ResponseEntity.ok(responseDTOS);
    }

    private static void validateCurrentUserIsAdmin() {
        if(!ContextHolder.getUserLogin().getRole().equals(RoleType.ADMIN))
            throw new NotAuthorizedException();
    }

    @Override
    public ResponseEntity<?> findReport(Long reportId) {
        Report byId = reportRepository.findById(reportId).orElseThrow(ReportNotFoundException::new);

        validateCurrentUserIsReporter(byId);

        ReportSimpleResponseDTO dto = ReportSimpleResponseDTO.toDTO(byId);

        return ResponseEntity.ok(dto);
    }

    private static void validateCurrentUserIsReporter(Report byId) {
        if(!Objects.equals(byId.getReporter().getId(), ContextHolder.getUserLoginId()) || !ContextHolder.getUserLogin().getRole().equals(RoleType.ADMIN)) {
            throw new NotAuthorizedException();
        }
    }
}

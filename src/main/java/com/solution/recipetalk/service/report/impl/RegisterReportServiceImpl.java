package com.solution.recipetalk.service.report.impl;

import com.solution.recipetalk.domain.report.entity.Report;
import com.solution.recipetalk.domain.report.entity.ReportState;
import com.solution.recipetalk.domain.report.repository.ReportRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.report.ReportRegisterDTO;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.report.RegisterReportService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class RegisterReportServiceImpl implements RegisterReportService {
    private final ReportRepository reportRepository;
    private final UserDetailRepository userDetailRepository;

    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> addReport(ReportRegisterDTO dto) {
        UserDetail reporter = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);

        Report report = Report.builder()
                .reporter(reporter)
                .description(dto.getDescription())
                .reportState(ReportState.신청)
                .build();

        reportRepository.save(report);

        return ResponseEntity.ok(null);
    }
}

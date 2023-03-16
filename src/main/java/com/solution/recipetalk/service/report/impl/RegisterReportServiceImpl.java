package com.solution.recipetalk.service.report.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.report.entity.Report;
import com.solution.recipetalk.domain.report.entity.ReportState;
import com.solution.recipetalk.domain.report.repository.ReportRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.report.ReportRegisterDTO;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.report.RegisterReportService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterReportServiceImpl implements RegisterReportService {
    private final ReportRepository reportRepository;
    private final UserDetailRepository userDetailRepository;

    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> addReport(Long reportedId, ReportRegisterDTO dto) {
        UserDetail reporter = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);
        UserDetail reported = userDetailRepository.findById(reportedId).orElseThrow(UserNotFoundException::new);

        String dir = "";

        if(null != dto.getScreenshotFile()) {
            try {
                dir = s3Uploader.upload(dto.getScreenshotFile(), S3dir.REPORT_SCREENSHOT_DIR);
            } catch(IOException e) {
                throw new ImageUploadFailedException();
            }
        }

        Report report = Report.builder()
                .reporter(reporter)
                .reportee(reported)
                .description(dto.getDescription())
                .screenshotURI(dir)
                .reportState(ReportState.신청)
                .build();

        reportRepository.save(report);

        return ResponseEntity.ok(null);
    }
}

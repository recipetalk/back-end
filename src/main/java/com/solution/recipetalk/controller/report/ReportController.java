package com.solution.recipetalk.controller.report;

import com.solution.recipetalk.dto.report.ReportRegisterDTO;
import com.solution.recipetalk.service.report.FindReportService;
import com.solution.recipetalk.service.report.RegisterReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class ReportController {
    private final RegisterReportService registerReportService;
    private final FindReportService findReportService;

    @PostMapping("/report/{reportedId}")
    public ResponseEntity<?> reportAdd(
            @PathVariable(name = "reportedId") Long reportedId,
            @Valid ReportRegisterDTO dto
    ) {
        return registerReportService.addReport(reportedId, dto);
    }

    @GetMapping("/report/list")
    public ResponseEntity<?> reportListByReporterId(Pageable pageable) {
        return findReportService.findAllReportsByReporter(pageable);
    }

    @GetMapping("/report/details/{reportId}")
    public ResponseEntity<?> reportDetails(@PathVariable(name = "reportId") Long reportId) {
        return findReportService.findReport(reportId);
    }
}

package com.solution.recipetalk.service.report;

import com.solution.recipetalk.dto.report.ReportRegisterDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterReportService {
    ResponseEntity<?> addReport(ReportRegisterDTO dto);
}

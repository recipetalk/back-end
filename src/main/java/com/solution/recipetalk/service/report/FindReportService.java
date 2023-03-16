package com.solution.recipetalk.service.report;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FindReportService {
    ResponseEntity<?> findAllReportsByReporter(Pageable pageable);
    ResponseEntity<?> findReport(Long reportId);
}

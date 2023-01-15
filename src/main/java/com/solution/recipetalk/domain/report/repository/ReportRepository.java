package com.solution.recipetalk.domain.report.repository;

import com.solution.recipetalk.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}

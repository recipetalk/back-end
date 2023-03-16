package com.solution.recipetalk.dto.report;

import com.solution.recipetalk.domain.report.entity.Report;
import com.solution.recipetalk.domain.report.entity.ReportState;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportSimpleResponseDTO {
    private String reporterUsername;
    private String reporteeUsername;
    private ReportState reportState;

    public static ReportSimpleResponseDTO toDTO(Report entity) {
        return ReportSimpleResponseDTO.builder()
                .reporterUsername(entity.getReporter().getUsername())
                .reporteeUsername(entity.getReportee().getUsername())
                .reportState(entity.getReportState())
                .build();
    }
}

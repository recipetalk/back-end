package com.solution.recipetalk.dto.report;

import com.solution.recipetalk.domain.report.entity.ReportState;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ReportResponseDTO {
    private UserSimpleProfileDTO reporter;
    private String description;
    private ReportState reportState;

    public ReportResponseDTO(
            String reporterUsername, String reporterNickname, String reporterProfileImageURI,
            String description, ReportState reportState,
            Boolean reporterIsDeleted
                             ) {
        if(reporterIsDeleted) {
            reporter = new UserSimpleProfileDTO(null, "(삭제)", "");
        }
        else {
            reporter = new UserSimpleProfileDTO(reporterUsername, reporterNickname, reporterProfileImageURI);
        }
        this.description = description;
        this.reportState = reportState;
    }
}

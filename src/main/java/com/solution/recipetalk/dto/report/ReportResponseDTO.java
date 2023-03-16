package com.solution.recipetalk.dto.report;

import com.solution.recipetalk.domain.report.entity.ReportState;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ReportResponseDTO {
    private UserSimpleProfileDTO reporter;
    private UserSimpleProfileDTO reportee;
    private String description;
    private String screenshotURI;
    private ReportState reportState;

    public ReportResponseDTO(
            String reporterUsername, String reporterNickname, String reporterProfileImageURI,
            String reporteeUsername, String reporteeNickname, String reporteeProfileImageURI,
            String description, String screenshotURI, ReportState reportState,
            Boolean reporterIsDeleted, Boolean reporteeIsDeleted
                             ) {
        if(reporterIsDeleted) {
            reporter = new UserSimpleProfileDTO(null, "(삭제)", "");
        }
        if(reporteeIsDeleted) {
            reportee = new UserSimpleProfileDTO(null, "(삭제)", "");
        }

        if(!reporterIsDeleted && !reporteeIsDeleted) {
            reporter = new UserSimpleProfileDTO(reporterUsername, reporterNickname, reporterProfileImageURI);
            reportee = new UserSimpleProfileDTO(reporteeUsername, reporteeNickname, reporteeProfileImageURI);
        }
        this.description = description;
        this.screenshotURI = screenshotURI;
        this.reportState = reportState;
    }
}

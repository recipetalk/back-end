package com.solution.recipetalk.dto.report;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportRegisterDTO {
    @NonNull
    @Size(min = 1, max = 1000, message = "입력할 수 있는 양을 초과하였습니다.")
    private String description;
}

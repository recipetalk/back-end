package com.solution.recipetalk.exception.report;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class ReportNotFoundException extends CustomException {
    public ReportNotFoundException() {
        super(ErrorCode.REPORT_NOT_FOUND);
    }
}

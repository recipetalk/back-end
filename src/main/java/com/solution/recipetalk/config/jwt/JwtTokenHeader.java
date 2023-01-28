package com.solution.recipetalk.config.jwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenHeader {
    private String headerType;
    private String headerData;

    public JwtTokenHeader(String headerType, HttpServletRequest request){
        this.headerType = headerType;
        this.headerData = request.getHeader(headerType);
    }
}

package com.solution.recipetalk.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // common
    NOT_FOUND(HttpStatus.NOT_FOUND, "C001", "페이지를 찾을 수 없습니다"),
    NOT_AUTHORIZED_TO_MODIFY(HttpStatus.FORBIDDEN, "C003", "수정할 권한이 없습니다"),

    // signup
    AUTH_REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "U002", "인증번호를 다시 한번 요청해 주세요"),

    // board
    CANNOT_FIND_BOARD(HttpStatus.NOT_FOUND, "C001", "게시물이 삭제되었거나 찾을 수 없습니다"),

    // comment
    NO_COMMENT_FOUND(HttpStatus.NOT_FOUND, "C101", "존재하지 않는 댓글입니다"),

    NOT_ADMIN(HttpStatus.FORBIDDEN, "C103", "관리자가 아닌 사용자는 권한이 없습니다"),
    COMMENT_IS_DELETED(HttpStatus.NOT_FOUND, "C104", "삭제된 댓글입니다.");

    private HttpStatus httpStatus;
    private String message;
    private String code;

    ErrorCode(HttpStatus httpStatus, String code, String message){
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }
}

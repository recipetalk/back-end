package com.solution.recipetalk.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // common
    NOT_FOUND(HttpStatus.NOT_FOUND, "C001", "페이지를 찾을 수 없습니다"),
    NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "C003", "권한이 없습니다"),

    NOT_AUTHORIZED_TO_REMOVE(HttpStatus.FORBIDDEN, "C004", "삭제 권한이 없습니다"),
    NOT_AUTHORIZED_TO_MODIFY(HttpStatus.FORBIDDEN, "C005", "수정 권한이 없습니다"),

    // signup
    SMS_API_REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "U002", "인증번호를 다시 한번 요청해 주세요."),
    PHONE_AUTH_ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "U003", "인증번호를 요청해 주세요."),
    PHONE_AUTH_COUNT_OVER_EXCEPTION(HttpStatus.FORBIDDEN, "U004", "인증횟수를 초과하였습니다"),
    PHONE_AUTH_NOT_EQUAL_EXCEPTION(HttpStatus.FORBIDDEN, "U005", "인증번호가 맞지 않습니다."),

    DUPLICATED_USER_EXIST(HttpStatus.BAD_REQUEST, "U007", "중복된 아이디가 존재합니다."),
    UNVERIFIED_EXCEPTION(HttpStatus.BAD_REQUEST, "U008", "인증되지 않았습니다."),
    VERIFIED_EXCEPTION(HttpStatus.BAD_REQUEST, "U009", "이미 인증되었습니다."),
    AUTH_REQUEST_EXPIRED_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "U002", "인증이 되지 않았습니다."),
    VERIFICATION_FAILED_EXCEPTION(HttpStatus.BAD_REQUEST, "U003", "인증을 요청해 주세요"),
    DUPLICATED_NICKNAME_EXIST(HttpStatus.BAD_REQUEST, "U010", "중복된 닉네임이 존재합니다."),
    DUPLICATED_EMAIL_EXISTS(HttpStatus.BAD_REQUEST, "U011", "중복된 이메일이 존재합니다."),
    // board
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "C001", "게시물이 삭제되었거나 찾을 수 없습니다"),

    // comment
    NO_COMMENT_FOUND(HttpStatus.NOT_FOUND, "C101", "존재하지 않는 댓글입니다"),
    COMMENT_IS_DELETED(HttpStatus.NOT_FOUND, "C104", "삭제된 댓글입니다."),

    // s3
    IMAGE_UPLOAD_FAILED_EXCEPTION(HttpStatus.BAD_REQUEST, "I001", "이미지 업로드에 실패했습니다."),

    // ingredient
    INGREDIENT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "IN001", "식재료를 찾을 수 없습니다."),

    // ingredient_trimming
    INGREDIENT_TRIMMING_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "INT001", "손질법을 찾을 수 없습니다."),

    // ingredient_trimming_row
    INGREDIENT_TRIMMING_ROW_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "INTR001", "손질 순서 정보를 찾을 수 없습니다."),

    INGREDIENT_DESCRIPTION_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "IND001", "식재료 효능을 찾을 수 없습니다."),

    INGREDIENT_DESCRIPTION_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "IND002", "해당 식재료의 효능 정보가 이미 등록되어 있습니다."),
    // recipe
    RECIPE_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "R001", "레시피를 찾을 수 없습니다."),

    RECIPE_ROW_NOT_FOUND(HttpStatus.NOT_FOUND, "R002", "레시피 행을 찾을 수 없습니다."),

    // user
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "U001", "유저를 찾을 수 없습니다."),

    // user Follow
    USER_FOLLOW_NOT_FOUND(HttpStatus.NOT_FOUND, "UF001", "팔로우 되어 있지 않습니다."),
    USER_FOLLOW_EXIST(HttpStatus.FORBIDDEN, "UF002", "이미 팔로우 되어 있습니다."),
    // user Block
    USER_BLOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "UB001", "차단 되어 있지 않습니다."),
    USER_BLOCK_EXIST_EXCEPTION(HttpStatus.NOT_FOUND, "UB002", "이미 차단되어 있습니다."),
    USER_INFORMATION_NOT_MATCH(HttpStatus.BAD_REQUEST, "U002", "사용자 정보가 일치하지 않습니다."),

    REPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "REP001", "신고내역을 찾을 수 없습니다."),

    BARCODE_LENGTH_NOT_VALID(HttpStatus.BAD_REQUEST, "P001", "바코드 길이가 유효하지 않습니다."),

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P002", "제품을 찾을 수 없습니다."),


    // FCM Token
    FCM_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "FC001", "FCM 토큰을 찾을 수 없습니다.");

    private HttpStatus httpStatus;
    private String message;
    private String code;

    ErrorCode(HttpStatus httpStatus, String code, String message){
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }
}

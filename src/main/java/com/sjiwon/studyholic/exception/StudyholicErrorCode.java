package com.sjiwon.studyholic.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StudyholicErrorCode {
    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보가 존재하지 않습니다"),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다"),
    DUPLICATE_USER_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다"),
    DUPLICATE_USER_LOGIN_ID(HttpStatus.CONFLICT, "중복된 로그인 아이디입니다"),

    ;

    private final HttpStatus status;
    private final String message;

    StudyholicErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}

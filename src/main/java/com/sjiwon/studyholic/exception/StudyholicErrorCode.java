package com.sjiwon.studyholic.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StudyholicErrorCode {
    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보가 존재하지 않습니다"),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다"),

    ;

    private final HttpStatus status;
    private final String message;

    StudyholicErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}

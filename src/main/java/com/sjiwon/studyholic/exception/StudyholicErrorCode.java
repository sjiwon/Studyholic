package com.sjiwon.studyholic.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StudyholicErrorCode {

    ;

    private final HttpStatus status;
    private final String message;

    StudyholicErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}

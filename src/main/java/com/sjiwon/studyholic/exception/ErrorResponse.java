package com.sjiwon.studyholic.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private int status;
    private String description; // 상태 코드 설명 [BAD_REQUEST, NOT_FOUND, ..]
    private String message;

    private ErrorResponse(StudyholicErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.description = errorCode.getStatus().getReasonPhrase();
        this.message = errorCode.getMessage();
    }

    public static ErrorResponse of(StudyholicErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }
}

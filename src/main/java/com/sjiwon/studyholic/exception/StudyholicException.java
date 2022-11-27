package com.sjiwon.studyholic.exception;

import lombok.Getter;

@Getter
public class StudyholicException extends RuntimeException {
    private final StudyholicErrorCode errorCode;

    private StudyholicException(StudyholicErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static StudyholicException type(StudyholicErrorCode errorCode) {
        return new StudyholicException(errorCode);
    }
}

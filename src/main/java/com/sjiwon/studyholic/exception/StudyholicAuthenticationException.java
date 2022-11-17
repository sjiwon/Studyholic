package com.sjiwon.studyholic.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class StudyholicAuthenticationException extends AuthenticationException {
    private final String message;

    public StudyholicAuthenticationException(String message) {
        super(message);
        this.message = message;
    }

    public static StudyholicAuthenticationException type(String message) {
        return new StudyholicAuthenticationException(message);
    }
}

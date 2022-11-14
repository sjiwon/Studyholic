package com.sjiwon.studyholic.web.api;

import com.sjiwon.studyholic.exception.ErrorResponse;
import com.sjiwon.studyholic.exception.StudyholicErrorCode;
import com.sjiwon.studyholic.exception.StudyholicException;
import com.sjiwon.studyholic.web.api.authenticate.AuthenticationApiController;
import com.sjiwon.studyholic.web.api.user.UserApiController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {AuthenticationApiController.class, UserApiController.class})
public class ApiExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> catchAnotherArtException(StudyholicException ex) {
        StudyholicErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.of(errorCode));
    }
}

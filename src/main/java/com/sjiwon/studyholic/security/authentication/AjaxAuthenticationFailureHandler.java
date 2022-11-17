package com.sjiwon.studyholic.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjiwon.studyholic.exception.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sjiwon.studyholic.exception.StudyholicErrorCode.USER_NOT_FOUND;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.WRONG_PASSWORD;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ErrorResponse errorResponse = null;
        if (exception instanceof UsernameNotFoundException) {
            errorResponse = ErrorResponse.of(USER_NOT_FOUND);
        } else if (exception instanceof BadCredentialsException) {
            errorResponse = ErrorResponse.of(WRONG_PASSWORD);
        }

        response.setStatus(errorResponse.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}

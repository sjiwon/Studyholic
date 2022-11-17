package com.sjiwon.studyholic.security.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjiwon.studyholic.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sjiwon.studyholic.exception.StudyholicErrorCode.USER_NOT_FOUND;
import static com.sjiwon.studyholic.exception.StudyholicErrorCode.WRONG_PASSWORD;

public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorResponse = null;
        if (authException instanceof UsernameNotFoundException) {
            errorResponse = ErrorResponse.of(USER_NOT_FOUND);
        } else if (authException instanceof BadCredentialsException) {
            errorResponse = ErrorResponse.of(WRONG_PASSWORD);
        }
        response.sendError(HttpStatus.UNAUTHORIZED.value(), objectMapper.writeValueAsString(errorResponse));
    }
}

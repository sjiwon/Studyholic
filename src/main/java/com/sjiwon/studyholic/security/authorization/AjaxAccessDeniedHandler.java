package com.sjiwon.studyholic.security.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjiwon.studyholic.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sjiwon.studyholic.exception.StudyholicErrorCode.REQUEST_FORBIDDEN;

public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendError(HttpStatus.FORBIDDEN.value(), objectMapper.writeValueAsString(ErrorResponse.of(REQUEST_FORBIDDEN)));
    }
}

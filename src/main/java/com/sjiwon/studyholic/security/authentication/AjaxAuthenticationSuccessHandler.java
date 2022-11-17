package com.sjiwon.studyholic.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjiwon.studyholic.common.VariableFactory;
import com.sjiwon.studyholic.domain.entity.AbstractUserPrincipal;
import com.sjiwon.studyholic.security.principal.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.sjiwon.studyholic.common.VariableFactory.SESSION_KEY;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}

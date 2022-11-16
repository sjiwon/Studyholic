package com.sjiwon.studyholic.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjiwon.studyholic.security.principal.UserPrincipal;
import com.sjiwon.studyholic.security.token.AjaxAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AjaxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher DEFAULT_AJAX_LOGIN_URL = new AntPathRequestMatcher("/api/login", "POST");
    private ObjectMapper objectMapper = new ObjectMapper();

    public AjaxAuthenticationFilter() {
        super(DEFAULT_AJAX_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        isAjaxRequest(request);
        UserPrincipal user = objectMapper.readValue(request.getReader(), UserPrincipal.class);
        validateAjaxRequestBodyData(user);

        // Authentication By AjaxAuthenticationToken
        AjaxAuthenticationToken ajaxAuthenticationToken = new AjaxAuthenticationToken(user.getUsername(), user.getPassword());
        return this.getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }

    private void isAjaxRequest(HttpServletRequest request) {
        if (Objects.isNull(request.getHeader("Content-Type")) && !Objects.equals(request.getHeader("Content-Type"), "application/json")) {
            throw new IllegalStateException("Authentication is not supported");
        }
    }

    private void validateAjaxRequestBodyData(UserPrincipal user) {
        if (!StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword())) {
            throw new IllegalArgumentException("Username or Password is not acceptable");
        }
    }
}

package com.sjiwon.studyholic.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjiwon.studyholic.exception.StudyholicAuthenticationException;
import com.sjiwon.studyholic.security.principal.UserLoginRequest;
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
        isAjaxRequest(request, response);
        UserLoginRequest user = objectMapper.readValue(request.getReader(), UserLoginRequest.class);
        validateAjaxRequestBodyData(user, response);

        // Authentication By AjaxAuthenticationToken
        AjaxAuthenticationToken ajaxAuthenticationToken = new AjaxAuthenticationToken(user.getLoginId(), user.getLoginPassword());
        return this.getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }

    private void isAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
        if (Objects.isNull(request.getHeader("Content-Type")) && !Objects.equals(request.getHeader("Content-Type"), "application/json")) {
            throw StudyholicAuthenticationException.type("인증방식이 올바르지 않습니다");
        }
    }

    private void validateAjaxRequestBodyData(UserLoginRequest user, HttpServletResponse response) throws IOException {
        if (!StringUtils.hasText(user.getLoginId()) || !StringUtils.hasText(user.getLoginPassword())) {
            throw StudyholicAuthenticationException.type("아이디나 비밀번호를 정확하게 입력해주세요");
        }
    }
}

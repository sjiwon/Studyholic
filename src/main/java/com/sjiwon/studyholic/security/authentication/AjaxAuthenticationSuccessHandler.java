package com.sjiwon.studyholic.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjiwon.studyholic.domain.etc.session.dto.UserSessionDto;
import com.sjiwon.studyholic.security.principal.UserAuthenticationDto;
import com.sjiwon.studyholic.security.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static com.sjiwon.studyholic.common.VariableFactory.SESSION_KEY;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${server.port}")
    private String portNum;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 별도의 세션에 사용자의 [닉네임, 이미지 서버 저장명] 제공
        generateSpecialStorageSession(request, authentication);
        
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String redirectUrl;
        if (Objects.isNull(savedRequest)) {
            redirectUrl = "/";
        } else {
            String url = savedRequest.getRedirectUrl();
            redirectUrl = url.substring(url.lastIndexOf(portNum) + portNum.length());
        }

        objectMapper.writeValue(response.getWriter(), redirectUrl);
    }

    private void generateSpecialStorageSession(HttpServletRequest request, Authentication authentication) {
        HttpSession session = request.getSession();
        UserAuthenticationDto user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        session.setAttribute(SESSION_KEY, new UserSessionDto(user.getNickname(), user.getStorageName()));
    }
}

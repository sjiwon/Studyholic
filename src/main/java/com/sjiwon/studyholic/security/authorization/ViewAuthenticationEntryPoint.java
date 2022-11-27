package com.sjiwon.studyholic.security.authorization;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import static com.sjiwon.studyholic.common.VariableFactory.LOCALE_KOREA;

public class ViewAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Locale locale = request.getLocale();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        if (locale.getLanguage().equalsIgnoreCase(LOCALE_KOREA)) { // locale: ko
            writer.println("<script>alert('로그인이 필요합니다'); location.href = '/login';</script>");
        } else { // locale: other
            writer.println("<script>alert('Login is required'); location.href = '/login';</script>");
        }
        writer.flush();
    }
}

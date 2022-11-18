package com.sjiwon.studyholic.security;

import com.sjiwon.studyholic.security.authentication.AjaxAuthenticationFailureHandler;
import com.sjiwon.studyholic.security.authentication.AjaxAuthenticationSuccessHandler;
import com.sjiwon.studyholic.security.authorization.AjaxAccessDeniedHandler;
import com.sjiwon.studyholic.security.authorization.AjaxAuthenticationEntryPoint;
import com.sjiwon.studyholic.security.filter.AjaxAuthenticationFilter;
import com.sjiwon.studyholic.security.provider.AjaxAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(1)
public class ApiSecurityConfig {
    private static final String[] AUTHENTICATION_POST_API_WHITELIST = {
            "/api/login", "/api/email/authenticate"
    };
    private static final String[] USER_POST_API_WHITELIST = {
            "/api/user", "/api/user/default-profile", "/api/user/duplicate-check", "/api/user/find/login-id", "/api/user/random-password"
    };

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationEntryPoint ajaxAthenticationEntryPoint() {
        return new AjaxAuthenticationEntryPoint();
    }

    @Bean
    AccessDeniedHandler ajaxAccessDeniedHandler() {
        return new AjaxAccessDeniedHandler();
    }

    @Bean
    AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }

    @Bean
    AuthenticationProvider ajaxAuthenticationProvider() {
        return new AjaxAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Bean
    AuthenticationManager ajaxAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        ProviderManager authenticationManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
        authenticationManager.getProviders().add(ajaxAuthenticationProvider());
        return authenticationManager;
    }

    @Bean
    AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
        AjaxAuthenticationFilter authenticationFilter = new AjaxAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(ajaxAuthenticationManager(authenticationConfiguration));
        authenticationFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
        return authenticationFilter;
    }

    @Bean
    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.antMatcher("/api/**")
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST, AUTHENTICATION_POST_API_WHITELIST).permitAll()
                .mvcMatchers(HttpMethod.POST, USER_POST_API_WHITELIST).permitAll()
                .anyRequest().authenticated();

        http.logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/");

        http.exceptionHandling()
                .authenticationEntryPoint(ajaxAthenticationEntryPoint())
                .accessDeniedHandler(ajaxAccessDeniedHandler());

        http.addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

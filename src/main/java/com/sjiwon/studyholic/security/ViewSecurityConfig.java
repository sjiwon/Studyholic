package com.sjiwon.studyholic.security;

import com.sjiwon.studyholic.security.authorization.ViewAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(2)
public class ViewSecurityConfig {
    private static final String[] VIEW_WHITELIST = {"/", "/login", "/signup", "/find-id", "/reset-password"};

    @Bean
    AuthenticationEntryPoint viewAuthenticationEntryPoint() {
        return new ViewAuthenticationEntryPoint();
    }

    @Bean
    SecurityFilterChain viewSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, VIEW_WHITELIST).permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(viewAuthenticationEntryPoint());

        return http.build();
    }
}

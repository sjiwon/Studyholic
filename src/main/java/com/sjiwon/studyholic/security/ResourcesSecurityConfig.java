package com.sjiwon.studyholic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(0)
public class ResourcesSecurityConfig {
    @Bean
    SecurityFilterChain resourcesSecurityFilterChain(HttpSecurity http) throws Exception {
        http.requestMatchers((matchers) -> matchers.antMatchers("/css/**", "/js/**", "/images/**", "favicon.ico"))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable();

        return http.build();
    }
}

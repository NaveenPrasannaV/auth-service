package com.secureflow.authservice.config;

import com.secureflow.authservice.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrfSpec -> csrfSpec.disable())
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers("/auth/**", "/test/**").permitAll()  //Public access
                        .pathMatchers("/admin/**").hasRole("ADMIN") //Only admin access
                        .pathMatchers("/api/**").hasAnyRole("USER", "ADMIN")  //Admin and User access
                        .anyExchange().authenticated()
                ).addFilterAfter(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

}

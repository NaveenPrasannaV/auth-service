package com.secureflow.authservice.service;

import com.secureflow.authservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Mono<String> login(String username, String password) {
        return userDetailsService.findByUsername(username)
//                .map(userDetails -> passwordEncoder.matches(password, userDetails.getPassword()))
//                .defaultIfEmpty(false);
                .flatMap(userDetails -> {
                    if (passwordEncoder.matches(password, userDetails.getPassword())) {
                        String token = jwtService.generateToken(userDetails.getUsername());
                        return Mono.just(token);
                    } else {
                        return Mono.error(new RuntimeException("Invalid Credentials"));
                    }
                });
    }

}

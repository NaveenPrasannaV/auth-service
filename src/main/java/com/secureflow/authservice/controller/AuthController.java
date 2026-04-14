package com.secureflow.authservice.controller;

import com.secureflow.authservice.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public Mono<String> login(@RequestBody LoginRequest request) {

    }
}
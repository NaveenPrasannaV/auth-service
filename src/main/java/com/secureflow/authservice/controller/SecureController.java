package com.secureflow.authservice.controller;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class SecureController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return ReactiveSecurityContextHolder.getContext()
                .map(context -> context.getAuthentication().getName())
                .map(username -> "Hello " + username + ", you're authenticated");
    }

    @GetMapping("/role")
    public Mono<String> role() {
        return ReactiveSecurityContextHolder.getContext()
                .map(context -> context.getAuthentication().getAuthorities())
                .map(auth -> "Your role: " + auth);
    }

}

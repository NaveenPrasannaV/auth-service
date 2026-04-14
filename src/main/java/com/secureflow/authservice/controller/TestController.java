package com.secureflow.authservice.controller;

import com.secureflow.authservice.model.User;
import com.secureflow.authservice.repository.UserRepository;
import com.secureflow.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/create")
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{username}")
    public Mono<User> getUser(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

}

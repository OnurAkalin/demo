package com.example.demo.controllers;

import com.example.demo.dtos.requests.AuthRequest;
import com.example.demo.dtos.requests.RegisterRequest;
import com.example.demo.dtos.responses.AuthResponse;
import com.example.demo.services.AuthService;
import com.example.demo.utils.result.DataResult;
import com.example.demo.utils.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public DataResult<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public Result register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

}
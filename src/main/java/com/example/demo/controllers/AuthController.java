package com.example.demo.controllers;

import com.example.demo.dtos.requests.AuthRequest;
import com.example.demo.dtos.requests.RegisterRequest;
import com.example.demo.dtos.responses.AuthResponse;
import com.example.demo.services.AuthService;
import com.example.demo.utils.result.DataResult;
import com.example.demo.utils.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DataResult<AuthResponse>> login(@Valid @RequestBody AuthRequest request) {
        DataResult<AuthResponse> result = authService.login(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<Result> register(@Valid @RequestBody RegisterRequest request) {
        Result result = authService.register(request);
        return new ResponseEntity<>(result, result.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

}
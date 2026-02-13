package com.example.demo.service;

import com.example.demo.dto.request.AuthRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.util.result.DataResult;
import com.example.demo.util.result.Result;

public interface AuthService {
    DataResult<AuthResponse> login(AuthRequest request);

    Result register(RegisterRequest request);
}
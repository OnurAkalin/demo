package com.example.demo.services;

import com.example.demo.dtos.requests.AuthRequest;
import com.example.demo.dtos.requests.RegisterRequest;
import com.example.demo.utils.result.DataResult;
import com.example.demo.dtos.responses.AuthResponse;
import com.example.demo.utils.result.Result;

public interface AuthService {
    DataResult<AuthResponse> login(AuthRequest request);
    Result register(RegisterRequest request);
}
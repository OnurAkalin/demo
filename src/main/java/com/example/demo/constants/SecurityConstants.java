package com.example.demo.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityConstants {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String[] PUBLIC_URLS = {
            "/swagger-ui/**",
            "/swagger/**",
            "/v3/api-docs/**",
            "/auth/**"
    };
    public static final String USER = "USER";
}
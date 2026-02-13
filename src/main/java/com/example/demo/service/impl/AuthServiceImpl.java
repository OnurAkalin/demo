package com.example.demo.service.impl;

import com.example.demo.constant.UIMessages;
import com.example.demo.dto.request.AuthRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import com.example.demo.service.AuthService;
import com.example.demo.util.result.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public DataResult<AuthResponse> login(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            final String token = jwtService.generateToken(userDetails);

            return new SuccessDataResult<>(new AuthResponse(token), UIMessages.LOGIN_SUCCESS);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return new ErrorDataResult<>(null, UIMessages.LOGIN_FAILURE);
        }
    }

    @Override
    public Result register(RegisterRequest request) {
        try {
            User user = userMapper.registerRequestToUser(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            userRepository.saveAndFlush(user);

            return new SuccessResult(UIMessages.SUCCESS);
        } catch (DataIntegrityViolationException e) {
            log.warn("Username is already in use: {}", request.getUsername());
            return new ErrorResult(UIMessages.ERROR);
        }
    }
}
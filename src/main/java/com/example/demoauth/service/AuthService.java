package com.example.demoauth.service;

import com.example.demoauth.dto.request.LoginRequestDto;
import com.example.demoauth.dto.request.RegisterRequestDto;
import com.example.demoauth.dto.response.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto request);

    void register(RegisterRequestDto request);

    Long count();
}

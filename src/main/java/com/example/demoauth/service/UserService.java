package com.example.demoauth.service;

import com.example.demoauth.dto.request.LoginRequestDto;
import com.example.demoauth.dto.request.RegisterRequestDto;
import com.example.demoauth.dto.response.LoginResponseDto;
import com.example.demoauth.enums.RoleType;

import java.util.List;

public interface UserService {

    LoginResponseDto login(LoginRequestDto request);

    void create(RegisterRequestDto request, List<RoleType> roles);

    void register(RegisterRequestDto request);

    Long count();
}

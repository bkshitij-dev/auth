package com.example.demoauth.service;

import com.example.demoauth.dto.LoginDto;
import com.example.demoauth.dto.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    void register(RegisterDto request);
}

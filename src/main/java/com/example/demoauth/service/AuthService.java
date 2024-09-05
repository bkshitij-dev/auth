package com.example.demoauth.service;

import com.example.demoauth.dto.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);
}

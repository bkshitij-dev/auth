package com.example.demoauth.service.impl;

import com.example.demoauth.dto.LoginDto;
import com.example.demoauth.dto.RegisterDto;
import com.example.demoauth.enums.RoleType;
import com.example.demoauth.model.User;
import com.example.demoauth.repository.UserRepository;
import com.example.demoauth.security.JwtTokenProvider;
import com.example.demoauth.service.AuthService;
import com.example.demoauth.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleService roleService;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    @Transactional
    public void register(RegisterDto request) {
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();
        RoleType role = request.getRole() != null ? RoleType.valueOf(request.getRole())
                : RoleType.ROLE_USER;
        user.addRole(roleService.findByName(role));
        userRepository.save(user);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}

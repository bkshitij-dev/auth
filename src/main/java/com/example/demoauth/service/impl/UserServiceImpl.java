package com.example.demoauth.service.impl;

import com.example.demoauth.dto.request.LoginRequestDto;
import com.example.demoauth.dto.request.RegisterRequestDto;
import com.example.demoauth.dto.response.LoginResponseDto;
import com.example.demoauth.enums.RoleType;
import com.example.demoauth.exception.AppException;
import com.example.demoauth.model.User;
import com.example.demoauth.repository.UserRepository;
import com.example.demoauth.security.JwtTokenProvider;
import com.example.demoauth.service.UserService;
import com.example.demoauth.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleService roleService;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(),request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return LoginResponseDto.builder().accessToken(token).build();
    }

    @Transactional
    @Override
    public void create(RegisterRequestDto request, List<RoleType> roles) {
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();
        roles.forEach(r -> user.addRole(roleService.findByName(r)));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void register(RegisterRequestDto request) {
        boolean existsByUsernameOrEmail = userRepository.existsByUsernameOrEmail(request.getUsername(),
                request.getEmail());
        if (existsByUsernameOrEmail) {
            throw new AppException("User with username/email already exists");
        }
        create(request, List.of(RoleType.ROLE_USER));
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}

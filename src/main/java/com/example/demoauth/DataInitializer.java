package com.example.demoauth;

import com.example.demoauth.dto.RegisterDto;
import com.example.demoauth.dto.RoleRequestDto;
import com.example.demoauth.service.AuthService;
import com.example.demoauth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleService roleService;
    private final AuthService authService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleService.count() <= 0) {
            createRoles();
        }
        if (authService.count() <= 0) {
            createUsers();
        }
    }

    private void createRoles() {
        RoleRequestDto adminRole = RoleRequestDto.builder().name("ROLE_ADMIN").build();
        roleService.create(adminRole);

        RoleRequestDto userRole = RoleRequestDto.builder().name("ROLE_USER").build();
        roleService.create(userRole);
    }

    private void createUsers() {
        RegisterDto admin = RegisterDto.builder()
                .name("Admin")
                .username("admin")
                .email("admin@app.com")
                .password("Admin@123")
                .role("ROLE_ADMIN")
                .build();
        authService.register(admin);

        RegisterDto user = RegisterDto.builder()
                .name("User")
                .username("user")
                .email("user@app.com")
                .password("User@123")
                .role("ROLE_USER")
                .build();
        authService.register(user);
    }
}

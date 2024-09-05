package com.example.demoauth;

import com.example.demoauth.dto.request.RegisterRequestDto;
import com.example.demoauth.dto.request.RoleRequestDto;
import com.example.demoauth.enums.RoleType;
import com.example.demoauth.service.UserService;
import com.example.demoauth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleService.count() <= 0) {
            createRoles();
        }
        if (userService.count() <= 0) {
            createUsers();
        }
    }

    private void createRoles() {
        RoleRequestDto adminRole = RoleRequestDto.builder().name(RoleType.ROLE_ADMIN.name()).build();
        roleService.create(adminRole);

        RoleRequestDto userRole = RoleRequestDto.builder().name(RoleType.ROLE_USER.name()).build();
        roleService.create(userRole);
    }

    private void createUsers() {
        RegisterRequestDto admin = RegisterRequestDto.builder()
                .name("Admin")
                .username("admin")
                .email("admin@app.com")
                .password("Admin@123")
                .build();
        userService.create(admin, List.of(RoleType.ROLE_ADMIN, RoleType.ROLE_USER));

        RegisterRequestDto user = RegisterRequestDto.builder()
                .name("User")
                .username("user")
                .email("user@app.com")
                .password("User@123")
                .build();
        userService.create(user, List.of(RoleType.ROLE_USER));
    }
}

package com.example.demoauth.service.impl;

import com.example.demoauth.dto.request.RoleRequestDto;
import com.example.demoauth.enums.RoleType;
import com.example.demoauth.model.Role;
import com.example.demoauth.repository.RoleRepository;
import com.example.demoauth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void create(RoleRequestDto request) {
        Role role = Role.builder()
                .name(RoleType.valueOf(request.getName()))
                .build();
        roleRepository.save(role);
    }

    @Override
    public Role findByName(RoleType name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Long count() {
        return roleRepository.count();
    }
}

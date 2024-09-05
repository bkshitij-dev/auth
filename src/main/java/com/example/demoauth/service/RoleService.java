package com.example.demoauth.service;

import com.example.demoauth.dto.RoleRequestDto;
import com.example.demoauth.model.Role;

public interface RoleService {

    void create(RoleRequestDto request);

    Role findByName(String name);

    Long count();
}

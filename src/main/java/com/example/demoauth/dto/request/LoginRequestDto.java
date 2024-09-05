package com.example.demoauth.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    private String usernameOrEmail;
    private String password;

}

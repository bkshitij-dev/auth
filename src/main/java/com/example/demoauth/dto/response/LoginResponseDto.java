package com.example.demoauth.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private String accessToken;
    @Builder.Default
    private String tokenType = "Bearer";
}

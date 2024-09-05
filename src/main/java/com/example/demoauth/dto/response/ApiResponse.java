package com.example.demoauth.dto.response;

import com.example.demoauth.enums.ResponseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {

    private String message;
    private Object data;
    private ResponseStatus status;
}

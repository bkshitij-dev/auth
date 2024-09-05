package com.example.demoauth.controller;

import com.example.demoauth.dto.response.ApiResponse;
import com.example.demoauth.enums.ResponseStatus;

public class BaseController {

    protected ApiResponse successResponse(String message, Object data) {
        return ApiResponse.builder()
                .status(ResponseStatus.SUCCESS)
                .message(message)
                .data(data)
                .build();
    }

    protected ApiResponse successResponse(String message) {
        return successResponse(message, null);
    }

    protected ApiResponse errorResponse(String message, Object data) {
        return ApiResponse.builder()
                .status(ResponseStatus.ERROR)
                .message(message)
                .data(data)
                .build();
    }
}

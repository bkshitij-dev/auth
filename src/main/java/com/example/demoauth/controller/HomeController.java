package com.example.demoauth.controller;

import com.example.demoauth.constant.AppConstants;
import com.example.demoauth.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController extends BaseController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<ApiResponse> helloAdmin(){
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, "Hello Admin"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<ApiResponse> helloUser(){
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, "Hello User"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> testUser(){
        return new ResponseEntity<>(successResponse(AppConstants.SUCCESS_RETRIEVE, "Hello Authenticated"),
                HttpStatus.OK);
    }
}

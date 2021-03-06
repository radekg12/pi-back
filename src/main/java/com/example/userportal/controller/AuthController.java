package com.example.userportal.controller;

import com.example.userportal.requestmodel.JwtAuthenticationResponse;
import com.example.userportal.requestmodel.SignInRequest;
import com.example.userportal.requestmodel.SignUpRequest;
import com.example.userportal.service.AuthService;
import com.example.userportal.service.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public JwtAuthenticationResponse authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {
        return authService.authenticateUser(signInRequest);
    }

    @PostMapping("/register")
    public CustomerDTO registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }
}

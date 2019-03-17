package com.example.userportal.controller;

import com.example.userportal.RequestModel.JwtAuthenticationResponse;
import com.example.userportal.RequestModel.SignInRequest;
import com.example.userportal.RequestModel.SignUpRequest;
import com.example.userportal.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  final AuthService authService;

  @PostMapping("/signin")
  public JwtAuthenticationResponse authenticateUser(@RequestBody SignInRequest signInRequest) {
    return authService.authenticateUser(signInRequest);
  }

  @PostMapping("/signup")
  public boolean registerUser(@RequestBody SignUpRequest signUpRequest) {
    return authService.registerUser(signUpRequest);
  }
}

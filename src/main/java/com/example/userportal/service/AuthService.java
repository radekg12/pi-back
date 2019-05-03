package com.example.userportal.service;

import com.example.userportal.requestmodel.JwtAuthenticationResponse;
import com.example.userportal.requestmodel.SignInRequest;
import com.example.userportal.requestmodel.SignUpRequest;
import com.example.userportal.service.dto.CustomerDTO;

public interface AuthService {
  JwtAuthenticationResponse authenticateUser(SignInRequest signInRequest);

  CustomerDTO registerUser(SignUpRequest signUpRequest);
}

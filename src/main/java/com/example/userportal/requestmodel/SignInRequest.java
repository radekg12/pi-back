package com.example.userportal.requestmodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInRequest {
  private String username;
  private String password;
  private Boolean rememberMe;
}

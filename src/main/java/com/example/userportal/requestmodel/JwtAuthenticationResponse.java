package com.example.userportal.requestmodel;

import com.example.userportal.domain.Authority;
import lombok.Data;

import java.util.Set;

@Data
public class JwtAuthenticationResponse {
  private String accessToken;
  private String tokenType = "Bearer";
  private Boolean rememberMe;
  private Set<Authority> authorities;

  public JwtAuthenticationResponse(String accessToken, Boolean rememberMe, Set<Authority> authorities) {
    this.accessToken = accessToken;
    this.rememberMe = rememberMe;
    this.authorities = authorities;
  }
}

package com.example.userportal.RequestModel;

import com.example.userportal.service.dto.UserAccountDTO;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
  private String accessToken;
  private String tokenType = "Bearer";
  private UserAccountDTO userDTO;

  public JwtAuthenticationResponse(String accessToken, UserAccountDTO userDTO) {
    this.accessToken = accessToken;
    this.userDTO = userDTO;
  }
}

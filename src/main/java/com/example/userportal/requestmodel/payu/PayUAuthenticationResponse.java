package com.example.userportal.requestmodel.payu;

import lombok.Data;

@Data
public class PayUAuthenticationResponse {
  private String access_token;
  private String token_type;
  private String refresh_token;
  private String expires_in;
  private String grant_type;
}

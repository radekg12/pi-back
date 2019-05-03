package com.example.userportal.requestmodel.payu;

import lombok.Data;

@Data
public class PayUOrderCreateResponse {
  private String redirectUri;
  private String orderId;

}

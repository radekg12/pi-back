package com.example.userportal.RequestModel;

import lombok.Data;

@Data
public class PayUOrderResponseModel {
  private String redirectUri;
  private String orderId;

}

package com.example.userportal.RequestModel;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PayUOrderRequest {
  private String notifyUrl;
  private String customerIp;
  private String merchantPosId;
  private String description;
  private String currencyCode;
  private int totalAmount;
  private List<PayUProduct> products;

}

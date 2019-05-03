package com.example.userportal.requestmodel.payu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayUOrderCreateRequest {
  private Integer extOrderId;
  private String continueUrl;
  private String notifyUrl;
  private String customerIp;
  private String merchantPosId;
  private String description;
  private String currencyCode;
  private String successCallback;
  private int totalAmount;
  private PayUProduct[] products;
  private Buyer buyer;
  private Settings settings;
  private PayMethods payMethods;
}


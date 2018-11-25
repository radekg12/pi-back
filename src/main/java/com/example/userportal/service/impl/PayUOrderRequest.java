package com.example.userportal.service.impl;

import lombok.Data;

@Data
public class PayUOrderRequest {
  private String notifyUrl;
  private String customerIp;
  private String merchantPosId;
  private String description;
  private String currencyCode;
  private String totalAmount;
  private Product[] products;


  @Data
  private class Product {
    private String name;
    private String unitPrice;
    private String quantity;
  }
}

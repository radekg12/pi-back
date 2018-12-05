package com.example.userportal.RequestModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayUProduct {
  private String name;
  private int unitPrice;
  private int quantity;
}

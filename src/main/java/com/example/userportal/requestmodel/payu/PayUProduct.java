package com.example.userportal.requestmodel.payu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayUProduct {
  private String name;
  private Integer unitPrice;
  private Integer quantity;
}

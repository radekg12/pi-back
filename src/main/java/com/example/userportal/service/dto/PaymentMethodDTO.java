package com.example.userportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {
  private int id;
  private String name;
  private int price;
}

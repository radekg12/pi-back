package com.example.userportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PayUOrderDTO {
  private AddressDTO address;
  private DeliveryTypeDTO deliveryType;
  private PaymentMethodDTO paymentMethod;
}

package com.example.userportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Collection;

@Builder
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
  private int id;
  private Timestamp dateOfOrder;
  private Timestamp dateOfDelivery;
  private int totalAmount;

  private OrderStatusDTO orderStatus;
  private DeliveryTypeDTO deliveryType;
  private PaymentMethodDTO paymentMethod;
  private AddressDTO address;
  private Collection<OrderPositionDTO> orderPositions;
}

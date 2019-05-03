package com.example.userportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collection;

@Builder
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusCategoryAllDTO {
  private Integer id;
  private String name;
  private Collection<OrderStatusDTO> orderStatuses;
}

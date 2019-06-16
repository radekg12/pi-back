package com.example.userportal.service.mapper;

import com.example.userportal.domain.OrderStatus;
import com.example.userportal.service.dto.OrderStatusSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusSmallMapper {

  OrderStatusSmallDTO toOrderStatusDto(OrderStatus orderStatus);

  @Mapping(target = "orders", ignore = true)
  @Mapping(target = "orderStatusCategory", ignore = true)
  OrderStatus toOrderStatus(OrderStatusSmallDTO orderStatusSmallDTO);

  List<OrderStatusSmallDTO> toOrderStatusSmallDtos(List<OrderStatus> orderStatusesSmall);

  List<OrderStatus> toOrderStatuses(List<OrderStatusSmallDTO> orderStatusDTOS);
}

package com.example.userportal.service.mapper;

import com.example.userportal.domain.OrderStatus;
import com.example.userportal.service.dto.OrderStatusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderStatusCategoryMapper.class)
public interface OrderStatusMapper {

  @Mapping(target = "orderStatusCategory", source = "orderStatusCategory")
  OrderStatusDTO toOrderStatusDto(OrderStatus orderStatus);

  @Mapping(target = "orders", ignore = true)
  @Mapping(target = "orderStatusCategory", source = "orderStatusCategory")
  OrderStatus toOrderStatus(OrderStatusDTO orderStatusDTO);

  List<OrderStatusDTO> toOrderStatusDtos(List<OrderStatus> orderStatuses);

  List<OrderStatus> toOrderStatuses(List<OrderStatusDTO> orderStatusDTOS);
}

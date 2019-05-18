package com.example.userportal.service.mapper;

import com.example.userportal.domain.OrderStatus;
import com.example.userportal.service.dto.OrderStatusSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusSmallMapper {

  OrderStatusSmallMapper MAPPER = Mappers.getMapper(OrderStatusSmallMapper.class);

  OrderStatusSmallDTO toOrderStatusDto(OrderStatus orderStatus);

  @Mapping(target = "ordersById", ignore = true)
  @Mapping(target = "orderStatusCategoryByOrderStatusCategoryId", ignore = true)
  OrderStatus toOrderStatus(OrderStatusSmallDTO orderStatusSmallDTO);

  List<OrderStatusSmallDTO> toOrderStatusSmallDtos(List<OrderStatus> orderStatusesSmall);

  List<OrderStatus> toOrderStatuses(List<OrderStatusSmallDTO> orderStatusDTOS);
}

package com.example.userportal.service.mapper;

import com.example.userportal.domain.OrderStatusCategory;
import com.example.userportal.service.dto.OrderStatusCategoryAllDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderStatusMapper.class)
public interface OrderStatusCategoryAllMapper {

  @Mapping(target = "orderStatuses", source = "orderStatuses")
  OrderStatusCategoryAllDTO toOrderStatusCategoryAllDto(OrderStatusCategory orderStatusCategory);

  @Mapping(target = "orderStatuses", source = "orderStatuses")
  OrderStatusCategory toOrderStatusCategory(OrderStatusCategoryAllDTO orderStatusCategoryAllDTO);

  List<OrderStatusCategoryAllDTO> toOrderStatusCategoryAllDtos(List<OrderStatusCategory> orderStatusCategories);

  List<OrderStatusCategory> toOrderStatusCategories(List<OrderStatusCategoryAllDTO> orderStatusCategoryAllDTOS);
}

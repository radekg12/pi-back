package com.example.userportal.service.mapper;

import com.example.userportal.domain.OrderStatusCategory;
import com.example.userportal.service.dto.OrderStatusCategoryAllDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderStatusMapper.class)
public interface OrderStatusCategoryAllMapper {

  OrderStatusCategoryAllMapper MAPPER = Mappers.getMapper(OrderStatusCategoryAllMapper.class);

  @Mapping(target = "orderStatuses", source = "orderStatusesById")
  OrderStatusCategoryAllDTO toOrderStatusCategoryAllDto(OrderStatusCategory orderStatusCategory);

  @Mapping(target = "orderStatusesById", source = "orderStatuses")
  OrderStatusCategory toOrderStatusCategory(OrderStatusCategoryAllDTO orderStatusCategoryAllDTO);

  List<OrderStatusCategoryAllDTO> toOrderStatusCategoryAllDtos(List<OrderStatusCategory> orderStatusCategories);

  List<OrderStatusCategory> toOrderStatusCategories(List<OrderStatusCategoryAllDTO> orderStatusCategoryAllDTOS);

  Iterable<OrderStatusCategoryAllDTO> toOrderStatusCategoryAllDtos(Iterable<OrderStatusCategory> orderStatusCategories);

  Iterable<OrderStatusCategory> toOrderStatusCategories(Iterable<OrderStatusCategoryAllDTO> orderStatusCategoryAllDTOS);

}

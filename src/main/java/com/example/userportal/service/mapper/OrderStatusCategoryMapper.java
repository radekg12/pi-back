package com.example.userportal.service.mapper;

import com.example.userportal.domain.OrderStatusCategory;
import com.example.userportal.service.dto.OrderStatusCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusCategoryMapper {

  OrderStatusCategoryDTO toOrderStatusCategoryDto(OrderStatusCategory orderStatusCategory);

  @Mapping(target = "orderStatusesById", ignore = true)
  OrderStatusCategory toOrderStatusCategory(OrderStatusCategoryDTO orderStatusCategoryDTO);

  List<OrderStatusCategoryDTO> toOrderStatusCategoryDtos(List<OrderStatusCategory> orderStatusCategories);

  List<OrderStatusCategory> toOrderStatusCategories(List<OrderStatusCategoryDTO> orderStatusCategoryDTOS);

}

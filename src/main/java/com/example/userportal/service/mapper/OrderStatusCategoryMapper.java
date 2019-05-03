package com.example.userportal.service.mapper;

import com.example.userportal.domain.OrderStatusCategory;
import com.example.userportal.service.dto.OrderStatusCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusCategoryMapper {

  OrderStatusCategoryMapper MAPPER = Mappers.getMapper(OrderStatusCategoryMapper.class);

  OrderStatusCategoryDTO toOrderStatusCategoryDto(OrderStatusCategory orderStatusCategory);

  @Mapping(target = "orderStatusesById", ignore = true)
  OrderStatusCategory toOrderStatusCategory(OrderStatusCategoryDTO orderStatusCategoryDTO);

  List<OrderStatusCategoryDTO> toOrderStatusCategoryDtos(List<OrderStatusCategory> orderStatusCategories);

  List<OrderStatusCategory> toOrderStatusCategories(List<OrderStatusCategoryDTO> orderStatusCategoryDTOS);

}

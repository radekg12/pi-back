package com.example.userportal.service.mapper;

import com.example.userportal.domain.OrderPosition;
import com.example.userportal.service.dto.OrderPositionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderPositionMapper {

  @Mapping(target = "product", source = "product")
  OrderPositionDTO toOrderPositionDto(OrderPosition orderPosition);

  @Mapping(target = "order", ignore = true)
  @Mapping(target = "product", source = "product")
  OrderPosition toOrderPosition(OrderPositionDTO orderPositionDTO);

  List<OrderPositionDTO> toOrderPositionDtos(List<OrderPosition> orderPosition);

  List<OrderPosition> toOrderPositions(List<OrderPositionDTO> orderPositionDTOS);

}

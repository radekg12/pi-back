package com.example.userportal.service.mapper;

import com.example.userportal.domain.DeliveryType;
import com.example.userportal.service.dto.DeliveryTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryTypeMapper {

  DeliveryTypeDTO toDeliveryTypeDto(DeliveryType deliveryType);

  @Mapping(target = "orders", ignore = true)
  DeliveryType toDeliveryType(DeliveryTypeDTO deliveryTypeDTO);

  List<DeliveryTypeDTO> toDeliveryTypeDtos(List<DeliveryType> deliveryTypes);

  List<DeliveryType> toDeliveryTypes(List<DeliveryTypeDTO> deliveryTypeDTOS);
}

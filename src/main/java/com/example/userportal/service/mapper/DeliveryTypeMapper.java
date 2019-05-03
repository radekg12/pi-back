package com.example.userportal.service.mapper;

import com.example.userportal.domain.DeliveryType;
import com.example.userportal.service.dto.DeliveryTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryTypeMapper {

  DeliveryTypeMapper MAPPER = Mappers.getMapper(DeliveryTypeMapper.class);

  DeliveryTypeDTO toDeliveryTypeDto(DeliveryType deliveryType);

  @Mapping(target = "ordersById", ignore = true)
  DeliveryType toDeliveryType(DeliveryTypeDTO deliveryTypeDTO);

  List<DeliveryTypeDTO> toDeliveryTypeDtos(List<DeliveryType> deliveryTypes);

  List<DeliveryType> toDeliveryTypes(List<DeliveryTypeDTO> deliveryTypeDTOS);

  Iterable<DeliveryTypeDTO> toDeliveryTypeDtos(Iterable<DeliveryType> deliveryTypes);

  Iterable<DeliveryType> toDeliveryTypes(Iterable<DeliveryTypeDTO> deliveryTypeDTOS);
}

package com.example.userportal.service.mapper;

import com.example.userportal.domain.SpecificationPosition;
import com.example.userportal.service.dto.SpecificationPositionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpecificationPositionMapper {

  SpecificationPositionMapper MAPPER = Mappers.getMapper(SpecificationPositionMapper.class);

  SpecificationPositionDTO toSpecificationPositionDto(SpecificationPosition specificationPosition);

  SpecificationPosition toSpecificationPosition(SpecificationPositionDTO specificationPositionDTO);
}

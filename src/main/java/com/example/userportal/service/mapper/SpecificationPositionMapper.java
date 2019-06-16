package com.example.userportal.service.mapper;

import com.example.userportal.domain.SpecificationPosition;
import com.example.userportal.service.dto.SpecificationPositionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecificationPositionMapper {

  SpecificationPositionDTO toSpecificationPositionDto(SpecificationPosition specificationPosition);

  SpecificationPosition toSpecificationPosition(SpecificationPositionDTO specificationPositionDTO);
}

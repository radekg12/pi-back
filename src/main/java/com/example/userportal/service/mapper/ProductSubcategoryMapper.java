package com.example.userportal.service.mapper;

import com.example.userportal.domain.ProductSubcategory;
import com.example.userportal.service.dto.ProductSubcategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductSubcategoryMapper {

  ProductSubcategoryMapper MAPPER = Mappers.getMapper(ProductSubcategoryMapper.class);

  ProductSubcategoryDTO toProductSubcategoryDto(ProductSubcategory productSubcategory);

  @Mapping(target = "products", ignore = true)
  @Mapping(target = "productCategory", ignore = true)
  ProductSubcategory toProductSubcategory(ProductSubcategoryDTO productSubcategoryDTO);
}
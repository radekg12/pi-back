package com.example.userportal.service.mapper;

import com.example.userportal.domain.ProductSubcategory;
import com.example.userportal.service.dto.ProductSubcategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductSubcategoryMapper {

  ProductSubcategoryDTO toProductSubcategoryDto(ProductSubcategory productSubcategory);

  @Mapping(target = "products", ignore = true)
  @Mapping(target = "productCategory", ignore = true)
  ProductSubcategory toProductSubcategory(ProductSubcategoryDTO productSubcategoryDTO);
}

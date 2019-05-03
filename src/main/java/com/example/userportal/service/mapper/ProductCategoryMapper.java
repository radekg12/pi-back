package com.example.userportal.service.mapper;

import com.example.userportal.domain.ProductCategory;
import com.example.userportal.service.dto.ProductCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ProductSubcategoryMapper.class)
public interface ProductCategoryMapper {

  ProductCategoryMapper MAPPER = Mappers.getMapper(ProductCategoryMapper.class);

  ProductCategoryDTO toProductCategoryDto(ProductCategory productCategory);

  ProductCategory toProductCategory(ProductCategoryDTO productCategoryDTO);

  Iterable<ProductCategoryDTO> toProductCategoryDtos(Iterable<ProductCategory> productCategories);

  Iterable<ProductCategory> toProductCategories(Iterable<ProductCategoryDTO> productCategoryDTOs);
}

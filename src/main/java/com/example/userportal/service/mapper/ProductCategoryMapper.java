package com.example.userportal.service.mapper;

import com.example.userportal.domain.ProductCategory;
import com.example.userportal.service.dto.ProductCategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductSubcategoryMapper.class)
public interface ProductCategoryMapper {

  ProductCategoryDTO toProductCategoryDto(ProductCategory productCategory);

  ProductCategory toProductCategory(ProductCategoryDTO productCategoryDTO);

  List<ProductCategoryDTO> toProductCategoryDtos(List<ProductCategory> productCategories);

  List<ProductCategory> toProductCategories(List<ProductCategoryDTO> productCategoryDTOs);
}

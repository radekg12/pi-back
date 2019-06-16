package com.example.userportal.service.mapper;

import com.example.userportal.domain.Product;
import com.example.userportal.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductSubcategoryMapper.class, SpecificationPositionMapper.class})
public interface ProductMapper {

  @Mapping(target = "subcategory", source = "productSubcategory")
  ProductDTO toProductDto(Product product);

  @Mapping(target = "productSubcategory", source = "subcategory")
  @Mapping(target = "orderPositions", ignore = true)
  @Mapping(target = "shoppingCartPositions", ignore = true)
  Product toProduct(ProductDTO productDto);

  List<ProductDTO> toProductDtos(List<Product> product);

  List<Product> toProducts(List<ProductDTO> productDto);

  default Page<ProductDTO> toPageOfProductDtos(Page<Product> productPage) {
    return productPage.map(this::toProductDto);
  }
}

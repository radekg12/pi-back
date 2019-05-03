package com.example.userportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data

@Accessors(chain = true)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private Integer id;
  private String name;
  private String description;
  private String company;
  private Integer physicalQuantityInStock;
  private Integer logicalQuantityInStock;
  private Integer unitPrice;
  private String imageUrl;

  private ProductSubcategoryDTO subcategory;
  private Collection<SpecificationPositionDTO> specificationPositions;
}

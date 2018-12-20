package com.example.userportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private int id;
  private String name;
  private String description;
  private String company;
  private int quantityInStock;
  private int unitPrice;
  private String imageUrl;

  private ProductSubcategoryDTO subcategory;
  private Collection<SpecificationPositionDTO> specificationPositions;
}

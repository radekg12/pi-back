package com.example.userportal.service;

import com.example.userportal.service.dto.ProductCategoryDTO;

public interface MenuService {
  Iterable<ProductCategoryDTO> findAll();

  ProductCategoryDTO findBySubcategoryId(int subcategoryId);
}

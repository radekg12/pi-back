package com.example.userportal.service;

import com.example.userportal.service.dto.ProductCategoryDTO;

import java.util.List;

public interface MenuService {
  List<ProductCategoryDTO> findAll();

  ProductCategoryDTO findBySubcategoryId(int subcategoryId);
}

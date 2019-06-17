package com.example.userportal.service.impl;

import com.example.userportal.domain.ProductCategory;
import com.example.userportal.repository.MenuRepository;
import com.example.userportal.service.MenuService;
import com.example.userportal.service.dto.ProductCategoryDTO;
import com.example.userportal.service.mapper.ProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuServiceImpl implements MenuService {

  private final MenuRepository repository;
  private final ProductCategoryMapper mapper;

  @Override
  public List<ProductCategoryDTO> findAll() {
    List<ProductCategory> productCategories = repository.findAll();
    return mapper.toProductCategoryDtos(productCategories);
  }

  @Override
  public ProductCategoryDTO findBySubcategoryId(int subcategoryId) {
    ProductCategory productCategory = repository.findProductCategoryByProductSubcategoryId(subcategoryId);
    return mapper.toProductCategoryDto(productCategory);
  }
}

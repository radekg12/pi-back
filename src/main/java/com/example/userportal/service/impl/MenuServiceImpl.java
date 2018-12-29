package com.example.userportal.service.impl;

import com.example.userportal.repository.MenuRepository;
import com.example.userportal.service.MenuService;
import com.example.userportal.service.dto.ProductCategoryDTO;
import com.example.userportal.service.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

  private final MenuRepository repository;
  private final ProductCategoryMapper mapper;

  @Autowired
  public MenuServiceImpl(MenuRepository repository, ProductCategoryMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Iterable<ProductCategoryDTO> findAll() {
    return mapper.toProductCategoryDtos(repository.findAll());
  }

  @Override
  public ProductCategoryDTO findBySubcategoryId(int subcategoryId) {
    return mapper.toProductCategoryDto(repository.findProductCategoryByProductSubcategoryId(subcategoryId));
  }
}

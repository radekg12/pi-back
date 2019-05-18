package com.example.userportal.service.impl;

import com.example.userportal.repository.MenuRepository;
import com.example.userportal.service.MenuService;
import com.example.userportal.service.dto.ProductCategoryDTO;
import com.example.userportal.service.mapper.ProductCategoryMapper;
import com.google.common.collect.Lists;
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
    return mapper.toProductCategoryDtos(Lists.newArrayList(repository.findAll()));
  }

  @Override
  public ProductCategoryDTO findBySubcategoryId(int subcategoryId) {
    return mapper.toProductCategoryDto(repository.findProductCategoryByProductSubcategoryId(subcategoryId));
  }
}

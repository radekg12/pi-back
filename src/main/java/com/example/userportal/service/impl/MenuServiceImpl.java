package com.example.userportal.service.impl;

import com.example.userportal.domain.ProductCategory;
import com.example.userportal.repository.MenuRepository;
import com.example.userportal.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

  private final MenuRepository repository;

  @Autowired
  public MenuServiceImpl(MenuRepository repository) {
    this.repository = repository;
  }

  @Override
  public Iterable<ProductCategory> findAll() {
    return repository.findAll();
  }
}

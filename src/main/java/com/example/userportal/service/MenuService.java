package com.example.userportal.service;

import com.example.userportal.domain.ProductCategory;

public interface MenuService {
  Iterable<ProductCategory> findAll();
}

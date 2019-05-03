package com.example.userportal.service;

import com.example.userportal.service.dto.OrderStatusCategoryAllDTO;

public interface OrderStatusCategoryService {

  Iterable<OrderStatusCategoryAllDTO> getCategories();
}

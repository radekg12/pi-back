package com.example.userportal.service;

import com.example.userportal.service.dto.OrderStatusCategoryAllDTO;

import java.util.List;

public interface OrderStatusCategoryService {

  List<OrderStatusCategoryAllDTO> getCategories();
}

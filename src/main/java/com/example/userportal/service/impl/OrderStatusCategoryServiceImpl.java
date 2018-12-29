package com.example.userportal.service.impl;

import com.example.userportal.repository.OrderStatusCategoryRepository;
import com.example.userportal.service.OrderStatusCategoryService;
import com.example.userportal.service.dto.OrderStatusCategoryAllDTO;
import com.example.userportal.service.mapper.OrderStatusCategoryAllMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusCategoryServiceImpl implements OrderStatusCategoryService {

  private final OrderStatusCategoryRepository repository;
  private final OrderStatusCategoryAllMapper mapper;

  @Autowired
  public OrderStatusCategoryServiceImpl(OrderStatusCategoryRepository repository, OrderStatusCategoryAllMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Iterable<OrderStatusCategoryAllDTO> getCategories() {
    return mapper.toOrderStatusCategoryAllDtos(repository.findAll());
  }
}

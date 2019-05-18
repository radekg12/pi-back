package com.example.userportal.service.impl;

import com.example.userportal.repository.OrderStatusCategoryRepository;
import com.example.userportal.service.OrderStatusCategoryService;
import com.example.userportal.service.dto.OrderStatusCategoryAllDTO;
import com.example.userportal.service.mapper.OrderStatusCategoryAllMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderStatusCategoryServiceImpl implements OrderStatusCategoryService {

  private final OrderStatusCategoryRepository repository;
  private final OrderStatusCategoryAllMapper mapper;

  @Override
  public List<OrderStatusCategoryAllDTO> getCategories() {
    return mapper.toOrderStatusCategoryAllDtos(Lists.newArrayList(repository.findAll()));
  }
}

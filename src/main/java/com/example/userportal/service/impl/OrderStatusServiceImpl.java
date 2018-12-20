package com.example.userportal.service.impl;

import com.example.userportal.domain.OrderStatus;
import com.example.userportal.repository.OrderStatusRepository;
import com.example.userportal.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

  private final OrderStatusRepository repository;

  @Autowired
  public OrderStatusServiceImpl(OrderStatusRepository repository) {
    this.repository = repository;
  }

  @Override
  public OrderStatus getStatusById(int id) {
    return repository.findById(id).orElse(null);
  }
}

package com.example.userportal.service.impl;

import com.example.userportal.domain.OrderStatus;
import com.example.userportal.repository.OrderStatusRepository;
import com.example.userportal.service.OrderStatusService;
import com.example.userportal.service.dto.OrderStatusDTO;
import com.example.userportal.service.mapper.OrderStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

  private final OrderStatusRepository repository;
  private final OrderStatusMapper mapper;

  @Autowired
  public OrderStatusServiceImpl(OrderStatusRepository repository, OrderStatusMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public OrderStatus getStatusById(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public Iterable<OrderStatusDTO> getStatuses() {
    return mapper.toOrderStatusDtos(repository.findAll());
  }
}

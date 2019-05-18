package com.example.userportal.service.impl;

import com.example.userportal.domain.OrderStatus;
import com.example.userportal.exception.ResourceNotFoundException;
import com.example.userportal.repository.OrderStatusRepository;
import com.example.userportal.service.OrderStatusService;
import com.example.userportal.service.dto.OrderStatusDTO;
import com.example.userportal.service.mapper.OrderStatusMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderStatusServiceImpl implements OrderStatusService {

  private final OrderStatusRepository repository;
  private final OrderStatusMapper mapper;

  @Override
  public OrderStatus getStatusById(int id) {
    return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order id=" + id + " could not be found"));
  }

  @Override
  public List<OrderStatusDTO> getStatuses() {
    return mapper.toOrderStatusDtos(Lists.newArrayList(repository.findAll()));
  }
}

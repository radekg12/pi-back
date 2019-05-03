package com.example.userportal.service.impl;

import com.example.userportal.domain.OrderStatus;
import com.example.userportal.exception.InternalServerErrorException;
import com.example.userportal.repository.OrderStatusRepository;
import com.example.userportal.service.OrderStatusService;
import com.example.userportal.service.dto.OrderStatusDTO;
import com.example.userportal.service.mapper.OrderStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderStatusServiceImpl implements OrderStatusService {

  private final OrderStatusRepository repository;
  private final OrderStatusMapper mapper;

  @Override
  public OrderStatus getStatusById(int id) {
    return repository.findById(id)
            .orElseThrow(() -> new InternalServerErrorException("Order id=" + id + " could not be found"));
  }

  @Override
  public Iterable<OrderStatusDTO> getStatuses() {
    return mapper.toOrderStatusDtos(repository.findAll());
  }
}

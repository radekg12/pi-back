package com.example.userportal.service;

import com.example.userportal.domain.OrderStatus;
import com.example.userportal.service.dto.OrderStatusDTO;

public interface OrderStatusService {

  OrderStatus getStatusById(int id);

  Iterable<OrderStatusDTO> getStatuses();
}

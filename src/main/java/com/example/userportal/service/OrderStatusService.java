package com.example.userportal.service;

import com.example.userportal.domain.OrderStatus;
import com.example.userportal.service.dto.OrderStatusDTO;

import java.util.List;

public interface OrderStatusService {

  OrderStatus getStatusById(int id);

  List<OrderStatusDTO> getStatuses();
}

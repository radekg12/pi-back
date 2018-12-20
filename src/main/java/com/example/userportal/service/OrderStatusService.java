package com.example.userportal.service;

import com.example.userportal.domain.OrderStatus;

public interface OrderStatusService {

  OrderStatus getStatusById(int id);
}

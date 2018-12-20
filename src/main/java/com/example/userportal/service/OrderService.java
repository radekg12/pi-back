package com.example.userportal.service;

import com.example.userportal.domain.Order;
import com.example.userportal.service.dto.OrderDTO;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
  Iterable<Order> findAll();

  Iterable<OrderDTO> findAllByCustomerId(int customerId);

  Order saveOrder(Order order);

  @Transactional
  void saveOrderAndCleanShoppingCart(Order order, int customerId);
}

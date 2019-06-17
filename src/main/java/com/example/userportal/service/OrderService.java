package com.example.userportal.service;

import com.example.userportal.domain.Order;
import com.example.userportal.service.dto.OrderDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
  List<OrderDTO> findAll();

  List<OrderDTO> findAllByCustomerId(int customerId);

  List<OrderDTO> findAllCurrentCustomerOrders();

  Order saveOrder(Order order);

  @Transactional
  void saveOrderAndCleanShoppingCart(Order order, int customerId);

  OrderDTO findById(int id);

  OrderDTO updateOrderStatus(int orderId, int statusId);
}

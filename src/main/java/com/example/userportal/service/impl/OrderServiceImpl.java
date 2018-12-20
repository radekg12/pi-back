package com.example.userportal.service.impl;

import com.example.userportal.domain.Order;
import com.example.userportal.repository.OrderRepository;
import com.example.userportal.repository.ShoppingCartRepository;
import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.OrderDTO;
import com.example.userportal.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;
  private final ShoppingCartRepository shoppingCartRepository;
  private final OrderMapper mapper;

  @Autowired
  public OrderServiceImpl(OrderRepository repository, ShoppingCartRepository shoppingCartRepository, OrderMapper mapper) {
    this.repository = repository;
    this.shoppingCartRepository = shoppingCartRepository;
    this.mapper = mapper;
  }

  @Override
  public Iterable<Order> findAll() {
    return repository.findAll();
  }


  @Override
  public Iterable<OrderDTO> findAllByCustomerId(int customerId) {
    Iterable<Order> orders = repository.findAllByCustomerId(customerId);
    return mapper.toOrderDtos(orders);
  }

  @Override
  public Order saveOrder(Order order) {
    return repository.save(order);
  }

  @Override
  public void saveOrderAndCleanShoppingCart(Order order, int customerId) {
    repository.save(order);
    shoppingCartRepository.deleteAllByCustomerId(customerId);
  }
}

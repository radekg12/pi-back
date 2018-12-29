package com.example.userportal.service.impl;

import com.example.userportal.domain.Order;
import com.example.userportal.domain.OrderStatus;
import com.example.userportal.repository.*;
import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.OrderDTO;
import com.example.userportal.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;
  private final OrderPositionRepository orderPositionRepository;
  private final ShoppingCartRepository shoppingCartRepository;
  private final ProductRepository productRepository;
  private final OrderStatusRepository orderStatusRepository;
  private final OrderMapper mapper;

  @Autowired
  public OrderServiceImpl(OrderRepository repository, OrderPositionRepository orderPositionRepository, ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, OrderStatusRepository orderStatusRepository, OrderMapper mapper) {
    this.repository = repository;
    this.orderPositionRepository = orderPositionRepository;
    this.shoppingCartRepository = shoppingCartRepository;
    this.productRepository = productRepository;
    this.orderStatusRepository = orderStatusRepository;
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
    Order saveOrder = repository.save(order);
    order.getOrderPositionsById().forEach(p ->
            productRepository.sellProducts(p.getProductByProductId().getId(), p.getQuantity()));
    order.getOrderPositionsById().forEach(p -> p.setOrderByOrderId(saveOrder));
    orderPositionRepository.saveAll(order.getOrderPositionsById());
    shoppingCartRepository.deleteAllByCustomerId(customerId);
  }

  @Override
  public OrderDTO findById(int id) {
    Order order = repository.findById(id).orElse(null);
    return mapper.toOrderDto(order);
  }

  @Override
  public OrderDTO updateStatus(int orderId, int statusId) {
    Order order = repository.findById(orderId).orElse(null);
    OrderStatus status = orderStatusRepository.findById(statusId).orElse(null);
    order.setOrderStatusByOrderStatusId(status);
    return mapper.toOrderDto(repository.save(order));
  }
}

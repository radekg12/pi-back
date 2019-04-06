package com.example.userportal.service.impl;

import com.example.userportal.domain.Order;
import com.example.userportal.domain.OrderPosition;
import com.example.userportal.domain.OrderStatus;
import com.example.userportal.domain.Product;
import com.example.userportal.repository.*;
import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.OrderDTO;
import com.example.userportal.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;
  private final OrderPositionRepository orderPositionRepository;
  private final ShoppingCartRepository shoppingCartRepository;
  private final ProductRepository productRepository;
  private final OrderStatusRepository orderStatusRepository;
  private final OrderMapper mapper;
  private final RecommendationServiceImpl recommendationService;

  @Override
  public Iterable<OrderDTO> findAll() {
    Iterable<Order> orders = repository.findAll();
    return mapper.toOrderDtos(orders);
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
    List<Product> products = order.getOrderPositionsById()
            .stream()
            .map(OrderPosition::getProductByProductId)
            .collect(Collectors.toList());
    recommendationService.addProductsRating(products);
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

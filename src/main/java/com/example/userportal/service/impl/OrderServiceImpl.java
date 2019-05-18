package com.example.userportal.service.impl;

import com.example.userportal.domain.Order;
import com.example.userportal.domain.OrderPosition;
import com.example.userportal.domain.OrderStatus;
import com.example.userportal.exception.ResourceNotFoundException;
import com.example.userportal.repository.*;
import com.example.userportal.security.SecurityUtils;
import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.OrderDTO;
import com.example.userportal.service.dto.ProductDTO;
import com.example.userportal.service.mapper.OrderMapper;
import com.example.userportal.service.mapper.ProductMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  private final ProductMapper productMapper;

  @Override
  public List<OrderDTO> findAll() {
    Iterable<Order> orders = repository.findAll();
    return mapper.toOrderDtos(Lists.newArrayList(orders));
  }

  @Override
  public List<OrderDTO> findAllByCustomerId(int customerId) {
    Iterable<Order> orders = repository.findAllByCustomerId(customerId);
    return mapper.toOrderDtos(Lists.newArrayList(orders));
  }

  @Override
  public List<OrderDTO> findAllCurrentCustomerOrders() {
    return findAllByCustomerId(SecurityUtils.getCurrentUserId());
  }

  @Override
  public Order saveOrder(Order order) {
    return repository.save(order);
  }

  @Transactional
  @Override
  public void saveOrderAndCleanShoppingCart(Order order, int customerId) {
    Order saveOrder = repository.save(order);
    order.getOrderPositionsById().forEach(p ->
            productRepository.sellProducts(p.getProductByProductId().getId(), p.getQuantity()));
    order.getOrderPositionsById().forEach(p -> p.setOrderByOrderId(saveOrder));
    orderPositionRepository.saveAll(order.getOrderPositionsById());
    shoppingCartRepository.deleteAllByCustomerId(customerId);

    List<ProductDTO> products = order.getOrderPositionsById()
            .stream()
            .map(OrderPosition::getProductByProductId)
            .map(productMapper::toProductDto)
            .collect(Collectors.toList());
    recommendationService.addProductsRating(products);
  }

  @Override
  public OrderDTO findById(int id) {
    Order order = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order id=" + id + " could not be found"));
    return mapper.toOrderDto(order);
  }

  @Override
  public OrderDTO updateStatus(int orderId, int statusId) {
    Order order = repository
            .findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order id=" + orderId + " could not be found"));
    OrderStatus status = orderStatusRepository
            .findById(statusId)
            .orElseThrow(() -> new ResourceNotFoundException("Status id=" + statusId + " could not be found"));
    order.setOrderStatusByOrderStatusId(status);
    return mapper.toOrderDto(repository.save(order));
  }
}

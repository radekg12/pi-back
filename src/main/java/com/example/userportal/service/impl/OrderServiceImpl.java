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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderPositionRepository orderPositionRepository;
  private final ShoppingCartRepository shoppingCartRepository;
  private final ProductRepository productRepository;
  private final OrderStatusRepository orderStatusRepository;
  private final OrderMapper orderMapper;

  private final RecommendationServiceImpl recommendationService;
  private final ProductMapper productMapper;

  @Override
  public List<OrderDTO> findAll() {
    List<Order> orders = orderRepository.findAll();
    return orderMapper.toOrderDtos(orders);
  }

  @Override
  public List<OrderDTO> findAllByCustomerId(int customerId) {
    List<Order> orders = orderRepository.findAllByCustomerId(customerId);
    return orderMapper.toOrderDtos(orders);
  }

  @Override
  public List<OrderDTO> findAllCurrentCustomerOrders() {
    return findAllByCustomerId(SecurityUtils.getCurrentUserId());
  }

  @Override
  public Order saveOrder(Order order) {
    return orderRepository.save(order);
  }

  @Transactional
  @Override
  public void saveOrderAndCleanShoppingCart(Order order, int customerId) {
    Order saveOrder = orderRepository.save(order);
    order.getOrderPositions().forEach(p -> productRepository.sellProducts(p.getProduct().getId(), p.getQuantity()));
    order.getOrderPositions().forEach(p -> p.setOrder(saveOrder));
    orderPositionRepository.saveAll(order.getOrderPositions());
    shoppingCartRepository.deleteAllByCustomerId(customerId);
    List<ProductDTO> products = getProducts(order);
    recommendationService.addProductsRating(products);
  }

  @Override
  public OrderDTO findById(int id) {
    Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order id=" + id + " could not be found"));
    return orderMapper.toOrderDto(order);
  }

  @Transactional
  @Override
  public OrderDTO updateOrderStatus(int orderId, int statusId) {
    Order order = orderRepository
            .findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order id=" + orderId + " could not be found"));
    OrderStatus status = orderStatusRepository
            .findById(statusId)
            .orElseThrow(() -> new ResourceNotFoundException("Status id=" + statusId + " could not be found"));
    order.setOrderStatus(status);
    return orderMapper.toOrderDto(orderRepository.save(order));
  }

  private List<ProductDTO> getProducts(Order order) {
    return order.getOrderPositions()
            .stream()
            .map(OrderPosition::getProduct)
            .map(productMapper::toProductDto)
            .collect(Collectors.toList());
  }
}

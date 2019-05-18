package com.example.userportal.controller;


import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/orders"})
public class OrderController {

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER')")
  @GetMapping(path = "/all")
  public List<OrderDTO> findAll() {
    return orderService.findAll();
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping
  public List<OrderDTO> findCurrentCustomerOrders() {
    return orderService.findAllCurrentCustomerOrders();
  }

  @GetMapping(path = "/{id}")
  public OrderDTO findById(
          @PathVariable("id") int id) {
    return orderService.findById(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(path = "/byCustomer/{id}")
  public List<OrderDTO> findAllByCustomerId(
          @PathVariable("id") int customerId) {
    return orderService.findAllByCustomerId(customerId);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER')")
  @PostMapping(path = "/{id}")
  public OrderDTO changeOrderStatus(
          @PathVariable("id") int orderId,
          @RequestParam(value = "statusId") int statusId) {
    return orderService.updateStatus(orderId, statusId);
  }
}

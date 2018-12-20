package com.example.userportal.controller;


import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/orders"})
public class OrderController {

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public Iterable<OrderDTO> findAll() {
    int customerId = 1;
    return orderService.findAllByCustomerId(customerId);
  }
}

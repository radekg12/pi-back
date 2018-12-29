package com.example.userportal.controller;


import com.example.userportal.service.OrderStatusService;
import com.example.userportal.service.dto.OrderStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/status"})
public class OrderStatusController {

  private final OrderStatusService service;

  @Autowired
  public OrderStatusController(OrderStatusService service) {
    this.service = service;
  }

  @GetMapping
  public Iterable<OrderStatusDTO> getStatuses() {
    return service.getStatuses();
  }
}

package com.example.userportal.controller;


import com.example.userportal.configuration.UserPrincipal;
import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/orders"})
public class OrderController {

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public Iterable<OrderDTO> findAll(Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getCustomerId();
    return orderService.findAllByCustomerId(customerId);
  }

  @GetMapping(path = "/{id}")
  public OrderDTO findAllById(
          @PathVariable("id") int id) {
    return orderService.findById(id);
  }

  @GetMapping(path = "/byCustomer/{id}")
  public Iterable<OrderDTO> findAllByCustomerId(
          @PathVariable("id") int customerId) {
    return orderService.findAllByCustomerId(customerId);
  }

  @PostMapping(path = "/{id}")
  public OrderDTO changeOrderStatus(
          @PathVariable("id") int orderId,
          @RequestParam(value = "statusId") int statusId) {
    return orderService.updateStatus(orderId, statusId);
  }
}

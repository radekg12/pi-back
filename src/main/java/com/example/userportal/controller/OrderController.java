package com.example.userportal.controller;


import com.example.userportal.security.jwt.UserPrincipal;
import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(path = "/all")
  public Iterable<OrderDTO> findAll() {
    return orderService.findAll();
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping
  public Iterable<OrderDTO> findMyOrders(Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getId();
    return orderService.findAllByCustomerId(customerId);
  }

  @GetMapping(path = "/{id}")
  public OrderDTO findById(
          @PathVariable("id") int id) {
    return orderService.findById(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(path = "/byCustomer/{id}")
  public Iterable<OrderDTO> findAllByCustomerId(
          @PathVariable("id") int customerId) {
    return orderService.findAllByCustomerId(customerId);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(path = "/{id}")
  public OrderDTO changeOrderStatus(
          @PathVariable("id") int orderId,
          @RequestParam(value = "statusId") int statusId) {
    return orderService.updateStatus(orderId, statusId);
  }
}

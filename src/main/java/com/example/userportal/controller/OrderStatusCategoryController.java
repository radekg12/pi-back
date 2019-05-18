package com.example.userportal.controller;


import com.example.userportal.service.OrderStatusCategoryService;
import com.example.userportal.service.dto.OrderStatusCategoryAllDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/statusCategory"})
public class OrderStatusCategoryController {

  private final OrderStatusCategoryService service;

  @Autowired
  public OrderStatusCategoryController(OrderStatusCategoryService service) {
    this.service = service;
  }

  @GetMapping
  public List<OrderStatusCategoryAllDTO> getStatuses() {
    return service.getCategories();
  }
}

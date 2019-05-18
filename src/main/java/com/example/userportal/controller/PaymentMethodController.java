package com.example.userportal.controller;


import com.example.userportal.domain.PaymentMethod;
import com.example.userportal.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/paymentMethod"})
public class PaymentMethodController {

  private final PaymentMethodService service;

  @Autowired
  public PaymentMethodController(PaymentMethodService service) {
    this.service = service;
  }

  @GetMapping
  public List<PaymentMethod> findAllByPage() {
    return service.findAll();
  }
}

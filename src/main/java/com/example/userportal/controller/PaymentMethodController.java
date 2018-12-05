package com.example.userportal.controller;


import com.example.userportal.domain.PaymentMethod;
import com.example.userportal.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.55:4200", "http://192.168.0.55:8081", "http://localhost:8081"}, maxAge = 3600)
@RestController
@RequestMapping({"/paymentMethod"})
public class PaymentMethodController {

  private final PaymentMethodService service;

  @Autowired
  public PaymentMethodController(PaymentMethodService service) {
    this.service = service;
  }

  @GetMapping
  public Iterable<PaymentMethod> findAllByPage() {
    return service.findAll();
  }
}

package com.example.userportal.controller;

import com.example.userportal.service.PayUClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.55:4200", "http://192.168.0.55:8081", "http://localhost:8081"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/payu")
public class PayUController {

  private final PayUClient payUClient;

  @Autowired
  PayUController(PayUClient payUClient) {
    this.payUClient = payUClient;
  }

  @PostMapping(value = "/make/payment")
  public String makePayment() {
    return payUClient.createPayment();
  }

  @PostMapping(value = "/complete/payment")
  public String completePayment(HttpServletRequest request) {
    return payUClient.completePayment(request);
  }
}

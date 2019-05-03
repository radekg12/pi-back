package com.example.userportal.controller;

import com.example.userportal.service.PayPalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {

  private final PayPalClient payPalClient;

  @Autowired
  PayPalController(PayPalClient payPalClient) {
    this.payPalClient = payPalClient;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping(value = "/make/payment")
  public Map<String, Object> makePayment(@RequestParam("sum") String sum) {
    return payPalClient.createPayment(sum);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping(value = "/complete/payment")
  public String completePayment(HttpServletRequest request) {
    return payPalClient.completePayment(request);
  }
}

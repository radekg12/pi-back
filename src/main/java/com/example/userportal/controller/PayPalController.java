package com.example.userportal.controller;

import com.example.userportal.service.PayPalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.55:4200", "http://192.168.0.55:8081", "http://localhost:8081"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {

  private final PayPalClient payPalClient;

  @Autowired
  PayPalController(PayPalClient payPalClient) {
    this.payPalClient = payPalClient;
  }

  @PostMapping(value = "/make/payment")
  public Map<String, Object> makePayment(@RequestParam("sum") String sum) {
    return payPalClient.createPayment(sum);
  }

  @PostMapping(value = "/complete/payment")
  public String completePayment(HttpServletRequest request) {
    return payPalClient.completePayment(request);
  }
}

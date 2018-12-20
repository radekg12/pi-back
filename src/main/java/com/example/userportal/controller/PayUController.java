package com.example.userportal.controller;

import com.example.userportal.RequestModel.PayUOrderResponseModel;
import com.example.userportal.service.PayUClient;
import com.example.userportal.service.dto.PayUOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/payu")
public class PayUController {

  private final PayUClient payUClient;

  @Autowired
  PayUController(PayUClient payUClient) {
    this.payUClient = payUClient;
  }

  @PostMapping(value = "/make/payment")
  public PayUOrderResponseModel makePayment(@RequestBody PayUOrderDTO payUOrderDTO, HttpServletRequest request) {
    int customerId = 1;
    return payUClient.payForOrder(customerId, request, payUOrderDTO);
  }

}

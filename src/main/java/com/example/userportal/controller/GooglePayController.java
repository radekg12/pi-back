package com.example.userportal.controller;

import com.example.userportal.requestmodel.payu.PayUOrderCreateResponse;
import com.example.userportal.service.PayUClient;
import com.example.userportal.service.dto.GooglePayOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "payment/googlepay")
public class GooglePayController {
  private final PayUClient payUClient;

  @Autowired
  GooglePayController(PayUClient payUClient) {
    this.payUClient = payUClient;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping
  public PayUOrderCreateResponse makePaymentWithGooglePay(@Valid @RequestBody GooglePayOrderDTO googlePayOrder) {
    return payUClient.payForOrderWithGooglePay(googlePayOrder);
  }
}

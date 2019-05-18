package com.example.userportal.controller;

import com.example.userportal.requestmodel.payu.PayUOrderCreateResponse;
import com.example.userportal.requestmodel.payu.PayUOrderNotifyRequest;
import com.example.userportal.service.PayUClient;
import com.example.userportal.service.dto.OrderDTO;
import com.example.userportal.service.dto.PayUOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/payu")
public class PayUController {

  private final PayUClient payUClient;
  @Value("${payu.secondKey}")
  private String secondKey;

  @Autowired
  PayUController(PayUClient payUClient) {
    this.payUClient = payUClient;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping(value = "/make/payment")
  public PayUOrderCreateResponse makePayment(@Valid @RequestBody PayUOrderDTO payUOrderDTO, HttpServletRequest request) {
    return payUClient.payForOrder(request, payUOrderDTO);
  }

  @PostMapping(value = "/notify")
  public OrderDTO finalizePayment(
          @RequestHeader("OpenPayu-Signature") String signatureHeader,
          @RequestBody PayUOrderNotifyRequest notify) {

    return payUClient.finalizePayment(signatureHeader, notify);
  }

}

package com.example.userportal.service;

import com.example.userportal.requestmodel.payu.PayUAuthenticationResponse;
import com.example.userportal.requestmodel.payu.PayUOrderCreateResponse;
import com.example.userportal.requestmodel.payu.PayUOrderNotifyRequest;
import com.example.userportal.service.dto.GooglePayOrderDTO;
import com.example.userportal.service.dto.OrderDTO;
import com.example.userportal.service.dto.PayUOrderDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

public interface PayUClient {

  PayUOrderCreateResponse payForOrder(HttpServletRequest req, PayUOrderDTO payUOrderDTO);

  PayUOrderCreateResponse payForOrderWithGooglePay(HttpServletRequest req, GooglePayOrderDTO googlePayOrderDTO);

  PayUAuthenticationResponse createPayment(int customerId);

  @Transactional
  PayUOrderCreateResponse completePayment(HttpServletRequest req, PayUAuthenticationResponse payUAuthenticationResponse, PayUOrderDTO payUOrderDTO, String googlePaymentToken);

  OrderDTO finalizePayment(String signatureHeader, PayUOrderNotifyRequest notify);
}

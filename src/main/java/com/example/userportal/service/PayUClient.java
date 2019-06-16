package com.example.userportal.service;

import com.example.userportal.requestmodel.payu.PayUAuthenticationResponse;
import com.example.userportal.requestmodel.payu.PayUOrderCreateResponse;
import com.example.userportal.requestmodel.payu.PayUOrderNotifyRequest;
import com.example.userportal.service.dto.GooglePayOrderDTO;
import com.example.userportal.service.dto.OrderDTO;
import com.example.userportal.service.dto.PayUOrderDTO;
import org.springframework.transaction.annotation.Transactional;

public interface PayUClient {

  PayUOrderCreateResponse payForOrder(PayUOrderDTO payUOrderDTO);

  PayUOrderCreateResponse payForOrderWithGooglePay(GooglePayOrderDTO googlePayOrderDTO);

  PayUAuthenticationResponse createPayment(int customerId);

  @Transactional
  PayUOrderCreateResponse completePayment(String authenticationValue, PayUOrderDTO payUOrderDTO, String googlePaymentToken);

  OrderDTO finalizePayment(String signatureHeader, PayUOrderNotifyRequest notify);
}

package com.example.userportal.service;

import com.example.userportal.requestmodel.payu.PayUOrderCreateResponse;
import com.example.userportal.requestmodel.payu.PayUOrderNotifyRequest;
import com.example.userportal.requestmodel.payu.PayUResponse;
import com.example.userportal.service.dto.OrderDTO;
import com.example.userportal.service.dto.PayUOrderDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface PayUClient {

  PayUOrderCreateResponse payForOrder(int customerId, HttpServletRequest req, PayUOrderDTO payUOrderDTO);

  PayUResponse createPayment(int customerId) throws IOException;

  PayUOrderCreateResponse completePayment(int customerId, HttpServletRequest req, PayUResponse payUResponse, PayUOrderDTO payUOrderDTO) throws IOException;

  OrderDTO finalizePayment(String signatureHeader, PayUOrderNotifyRequest notify);
}

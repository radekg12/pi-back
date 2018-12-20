package com.example.userportal.service;

import com.example.userportal.RequestModel.PayUOrderResponseModel;
import com.example.userportal.RequestModel.PayUResponse;
import com.example.userportal.service.dto.PayUOrderDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface PayUClient {

  PayUOrderResponseModel payForOrder(int customerId, HttpServletRequest req, PayUOrderDTO payUOrderDTO);

  PayUResponse createPayment(int customerId) throws IOException, InterruptedException;

  PayUOrderResponseModel completePayment(int customerId, HttpServletRequest req, PayUResponse payUResponse, PayUOrderDTO payUOrderDTO) throws IOException, InterruptedException;
}

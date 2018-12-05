package com.example.userportal.service;

import com.example.userportal.RequestModel.PayUOrderResponseModel;
import com.example.userportal.RequestModel.PayUResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface PayUClient {

  PayUOrderResponseModel payForOrder(int customerId, HttpServletRequest req);

  PayUResponse createPayment(int customerId) throws IOException, InterruptedException;

  PayUOrderResponseModel completePayment(int customerId, HttpServletRequest req, PayUResponse payUResponse) throws IOException, InterruptedException;
}

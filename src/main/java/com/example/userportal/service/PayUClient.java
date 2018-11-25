package com.example.userportal.service;

import javax.servlet.http.HttpServletRequest;

public interface PayUClient {

  String createPayment();

  String completePayment(HttpServletRequest req);
}

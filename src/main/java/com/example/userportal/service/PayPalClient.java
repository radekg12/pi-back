package com.example.userportal.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface PayPalClient {

  Map<String, Object> createPayment(String sum);

  String completePayment(HttpServletRequest req);
}

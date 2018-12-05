package com.example.userportal.service;

import com.example.userportal.domain.PaymentMethod;

public interface PaymentMethodService {
  Iterable<PaymentMethod> findAll();

}

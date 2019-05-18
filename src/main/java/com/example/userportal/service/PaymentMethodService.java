package com.example.userportal.service;

import com.example.userportal.domain.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
  List<PaymentMethod> findAll();

}

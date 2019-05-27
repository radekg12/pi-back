package com.example.userportal.service;

import com.example.userportal.domain.PaymentMethod;
import com.example.userportal.service.dto.PaymentMethodDTO;

import java.util.List;

public interface PaymentMethodService {
  List<PaymentMethod> findAll();

  PaymentMethodDTO findById(int id);
}

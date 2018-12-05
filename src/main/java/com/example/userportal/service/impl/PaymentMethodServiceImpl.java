package com.example.userportal.service.impl;

import com.example.userportal.domain.PaymentMethod;
import com.example.userportal.repository.PaymentMethodRepository;
import com.example.userportal.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

  private final PaymentMethodRepository repository;

  @Autowired
  public PaymentMethodServiceImpl(PaymentMethodRepository repository) {
    this.repository = repository;
  }

  @Override
  public Iterable<PaymentMethod> findAll() {
    return repository.findAll();
  }
}

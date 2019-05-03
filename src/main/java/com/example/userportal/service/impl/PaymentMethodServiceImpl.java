package com.example.userportal.service.impl;

import com.example.userportal.domain.PaymentMethod;
import com.example.userportal.repository.PaymentMethodRepository;
import com.example.userportal.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentMethodServiceImpl implements PaymentMethodService {
  private final PaymentMethodRepository repository;

  @Override
  public Iterable<PaymentMethod> findAll() {
    return repository.findAll();
  }
}

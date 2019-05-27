package com.example.userportal.service.impl;

import com.example.userportal.domain.PaymentMethod;
import com.example.userportal.exception.ResourceNotFoundException;
import com.example.userportal.repository.PaymentMethodRepository;
import com.example.userportal.service.PaymentMethodService;
import com.example.userportal.service.dto.PaymentMethodDTO;
import com.example.userportal.service.mapper.PaymentMethodMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentMethodServiceImpl implements PaymentMethodService {
  private final PaymentMethodRepository repository;
  private final PaymentMethodMapper mapper;

  @Override
  public List<PaymentMethod> findAll() {
    return Lists.newArrayList(repository.findAll());
  }

  @Override
  public PaymentMethodDTO findById(int id) {
    return mapper.toPaymentMethodDto(repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Payment method id=" + id + " could not be found")));
  }
}

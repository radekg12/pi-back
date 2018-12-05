package com.example.userportal.service.impl;

import com.example.userportal.domain.DeliveryType;
import com.example.userportal.repository.DeliveryTypeRepository;
import com.example.userportal.service.DeliveryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

  private final DeliveryTypeRepository repository;

  @Autowired
  public DeliveryTypeServiceImpl(DeliveryTypeRepository repository) {
    this.repository = repository;
  }

  @Override
  public Iterable<DeliveryType> findAll() {
    return repository.findAll();
  }
}

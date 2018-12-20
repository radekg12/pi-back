package com.example.userportal.service.impl;

import com.example.userportal.domain.DeliveryType;
import com.example.userportal.repository.DeliveryTypeRepository;
import com.example.userportal.service.DeliveryTypeService;
import com.example.userportal.service.dto.DeliveryTypeDTO;
import com.example.userportal.service.mapper.DeliveryTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

  private final DeliveryTypeRepository repository;
  private final DeliveryTypeMapper mapper;

  @Autowired
  public DeliveryTypeServiceImpl(DeliveryTypeRepository repository, DeliveryTypeMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Iterable<DeliveryTypeDTO> findAll() {
    Iterable<DeliveryType> deliveryTypes = repository.findAll();
    return mapper.toDeliveryTypeDtos(deliveryTypes);
  }
}

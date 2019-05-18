package com.example.userportal.service.impl;

import com.example.userportal.domain.DeliveryType;
import com.example.userportal.repository.DeliveryTypeRepository;
import com.example.userportal.service.DeliveryTypeService;
import com.example.userportal.service.dto.DeliveryTypeDTO;
import com.example.userportal.service.mapper.DeliveryTypeMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

  private final DeliveryTypeRepository repository;
  private final DeliveryTypeMapper mapper;

  @Override
  public List<DeliveryTypeDTO> findAll() {
    Iterable<DeliveryType> deliveryTypes = repository.findAll();
    return mapper.toDeliveryTypeDtos(Lists.newArrayList(deliveryTypes));
  }
}

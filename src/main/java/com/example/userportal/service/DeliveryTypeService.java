package com.example.userportal.service;

import com.example.userportal.service.dto.DeliveryTypeDTO;

public interface DeliveryTypeService {
  Iterable<DeliveryTypeDTO> findAll();
}

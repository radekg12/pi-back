package com.example.userportal.service;

import com.example.userportal.domain.DeliveryType;

public interface DeliveryTypeService {
  Iterable<DeliveryType> findAll();
}

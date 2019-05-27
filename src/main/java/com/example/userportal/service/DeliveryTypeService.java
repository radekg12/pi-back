package com.example.userportal.service;

import com.example.userportal.service.dto.DeliveryTypeDTO;

import java.util.List;

public interface DeliveryTypeService {
  List<DeliveryTypeDTO> findAll();

  DeliveryTypeDTO findById(int id);
}

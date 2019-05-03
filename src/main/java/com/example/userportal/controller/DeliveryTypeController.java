package com.example.userportal.controller;


import com.example.userportal.service.DeliveryTypeService;
import com.example.userportal.service.dto.DeliveryTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/deliveryType"})
public class DeliveryTypeController {

  private final DeliveryTypeService deliveryService;

  @Autowired
  public DeliveryTypeController(DeliveryTypeService deliveryService) {
    this.deliveryService = deliveryService;
  }

  @GetMapping
  public Iterable<DeliveryTypeDTO> findAll() {
    return deliveryService.findAll();
  }
}

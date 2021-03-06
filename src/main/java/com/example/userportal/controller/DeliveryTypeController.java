package com.example.userportal.controller;


import com.example.userportal.service.DeliveryTypeService;
import com.example.userportal.service.dto.DeliveryTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/delivery-types")
public class DeliveryTypeController {
    private final DeliveryTypeService deliveryService;

    @Autowired
    public DeliveryTypeController(DeliveryTypeService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<DeliveryTypeDTO> findAll() {
        return deliveryService.findAll();
    }
}

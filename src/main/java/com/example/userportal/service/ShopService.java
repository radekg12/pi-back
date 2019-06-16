package com.example.userportal.service;

import com.example.userportal.service.dto.ShopDTO;

import java.util.List;

public interface ShopService {

    List<ShopDTO> findClosestShops(double latitude, double longitude);

}

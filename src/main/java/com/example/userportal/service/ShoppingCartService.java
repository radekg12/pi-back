package com.example.userportal.service;

import com.example.userportal.service.dto.ShoppingCartPositionDTO;

import java.util.List;

public interface ShoppingCartService {

  ShoppingCartPositionDTO addPosition(int productId);

  ShoppingCartPositionDTO getPosition(int id);

  List<ShoppingCartPositionDTO> getAllCurrentCustomerPositions();

  ShoppingCartPositionDTO getPosition(int customerId, int productId);

  ShoppingCartPositionDTO updatePositionQuantity(int productId, int quantity);

  ShoppingCartPositionDTO deletePosition(int productId);
}

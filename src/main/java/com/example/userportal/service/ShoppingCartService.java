package com.example.userportal.service;

import com.example.userportal.service.dto.ShoppingCartPositionDTO;

public interface ShoppingCartService {

  ShoppingCartPositionDTO addPosition(int customerId, int productId);

  ShoppingCartPositionDTO getPosition(int id);

  Iterable<ShoppingCartPositionDTO> getAllPositions(int customerId);

  ShoppingCartPositionDTO getPosition(int customerId, int productId);


  ShoppingCartPositionDTO updatePositionQuantity(int customerId, int productId, int quantity);

  ShoppingCartPositionDTO deletePosition(int customerId, int productId);
}

package com.example.userportal.service;

import com.example.userportal.domain.ShoppingCartPosition;

import java.util.Optional;

public interface ShoppingCartService {

  ShoppingCartPosition addPosition(int customerId, int productId);

  Optional<ShoppingCartPosition> getPosition(int id);

  Iterable<ShoppingCartPosition> getAllPositions(int customerId);

  ShoppingCartPosition getPosition(int customerId, int productId);


  ShoppingCartPosition updatePositionQuantity(int customerId, int productId, int quantity);

  ShoppingCartPosition deletePosition(int customerId, int productId);
}

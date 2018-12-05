package com.example.userportal.service.impl;

import com.example.userportal.domain.ShoppingCartPosition;
import com.example.userportal.repository.ShoppingCartRepository;
import com.example.userportal.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private final ShoppingCartRepository repository;


  @Autowired
  public ShoppingCartServiceImpl(ShoppingCartRepository repository) {
    this.repository = repository;
  }


  @Override
  public ShoppingCartPosition addPosition(int customerId, int productId) {

    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);

    return position
            .map(p -> {
              p.setQuantity(p.getQuantity() + 1);
              return repository.save(p);
            })
            .orElseGet(() -> {
              ShoppingCartPosition newPosition = ShoppingCartPosition.builder()
                      .customerId(customerId)
                      .productId(productId)
                      .quantity(1)
                      .build();
              return repository.save(newPosition);
            });

  }

  @Override
  public Optional<ShoppingCartPosition> getPosition(int positionId) {
    return repository.findById(positionId);
  }

  @Override
  public Iterable<ShoppingCartPosition> getAllPositions(int customerId) {
    return repository.findByCustomerId(customerId);
  }

  @Override
  public ShoppingCartPosition getPosition(int customerId, int productId) {
    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);

    return position.orElse(null);
  }

  @Override
  public ShoppingCartPosition updatePositionQuantity(int customerId, int productId, int quantity) {
    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);
    position.ifPresent(p -> {
              p.setQuantity(quantity);
              repository.save(p);
            }
    );

    return position.orElse(null);
  }

  @Override
  public ShoppingCartPosition deletePosition(int customerId, int productId) {
    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);
    position.ifPresent(repository::delete);

    return position.orElse(null);
  }
}

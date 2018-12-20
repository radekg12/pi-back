package com.example.userportal.service.impl;

import com.example.userportal.domain.ShoppingCartPosition;
import com.example.userportal.repository.ShoppingCartRepository;
import com.example.userportal.service.ShoppingCartService;
import com.example.userportal.service.dto.ShoppingCartPositionDTO;
import com.example.userportal.service.mapper.ShoppingCartPositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private final ShoppingCartRepository repository;
  private final ShoppingCartPositionMapper mapper;


  @Autowired
  public ShoppingCartServiceImpl(ShoppingCartRepository repository, ShoppingCartPositionMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }


  @Override
  public ShoppingCartPositionDTO addPosition(int customerId, int productId) {

    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);

    return position
            .map(p -> {
              p.setQuantity(p.getQuantity() + 1);
              return mapper.toShoppingCartPositionDto(repository.save(p));
            })
            .orElseGet(() -> {
              ShoppingCartPosition newPosition = ShoppingCartPosition.builder()
                      .customerId(customerId)
                      .productId(productId)
                      .quantity(1)
                      .build();


              return mapper.toShoppingCartPositionDto(repository.save(newPosition));
            });

  }

  @Override
  public ShoppingCartPositionDTO getPosition(int positionId) {
    ShoppingCartPosition position = repository.findById(positionId).orElse(null);
    return mapper.toShoppingCartPositionDto(position);
  }

  @Override
  public Iterable<ShoppingCartPositionDTO> getAllPositions(int customerId) {
    return mapper.toShoppingCartPositionDtos(repository.findByCustomerId(customerId));
  }

  @Override
  public ShoppingCartPositionDTO getPosition(int customerId, int productId) {
    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);

    return mapper.toShoppingCartPositionDto(position.orElse(null));
  }

  @Override
  public ShoppingCartPositionDTO updatePositionQuantity(int customerId, int productId, int quantity) {
    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);
    position.ifPresent(p -> {
              p.setQuantity(quantity);
              repository.save(p);
            }
    );

    return mapper.toShoppingCartPositionDto(position.orElse(null));
  }

  @Override
  public ShoppingCartPositionDTO deletePosition(int customerId, int productId) {
    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);
    position.ifPresent(repository::delete);

    return mapper.toShoppingCartPositionDto(position.orElse(null));
  }
}

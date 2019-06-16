package com.example.userportal.service.impl;

import com.example.userportal.domain.ShoppingCartPosition;
import com.example.userportal.exception.ResourceNotFoundException;
import com.example.userportal.repository.ShoppingCartRepository;
import com.example.userportal.security.SecurityUtils;
import com.example.userportal.service.ShoppingCartService;
import com.example.userportal.service.dto.ShoppingCartPositionDTO;
import com.example.userportal.service.mapper.ShoppingCartPositionMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartServiceImpl implements ShoppingCartService {
  private final ShoppingCartRepository repository;
  private final ShoppingCartPositionMapper mapper;

  @Override
  public ShoppingCartPositionDTO addPosition(int productId) {
    Integer customerId = SecurityUtils.getCurrentUserId();
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
    ShoppingCartPosition position = repository.findById(positionId)
            .orElseThrow(() -> new ResourceNotFoundException("Shopping cart position id=" + positionId + " could not be found"));
    return mapper.toShoppingCartPositionDto(position);
  }

  @Override
  public List<ShoppingCartPositionDTO> getAllCurrentCustomerPositions() {
    Integer customerId = SecurityUtils.getCurrentUserId();
    return mapper.toShoppingCartPositionDtos(Lists.newArrayList(repository.findByCustomerId(customerId)));
  }

  @Override
  public ShoppingCartPositionDTO getPosition(int customerId, int productId) {
    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);

    return mapper.toShoppingCartPositionDto(position.orElseThrow(() -> new ResourceNotFoundException("Shopping cart position id=" + position + " could not be found")));
  }

  @Override
  public ShoppingCartPositionDTO updatePositionQuantity(int productId, int quantity) {
    Integer customerId = SecurityUtils.getCurrentUserId();
    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);
    position.ifPresent(p -> {
              p.setQuantity(quantity);
              repository.save(p);
            }
    );

    return mapper.toShoppingCartPositionDto(position.orElseThrow(() -> new ResourceNotFoundException("Shopping cart position id=" + position + " could not be found")));
  }

  @Override
  public ShoppingCartPositionDTO deletePosition(int productId) {
    Integer customerId = SecurityUtils.getCurrentUserId();
    Optional<ShoppingCartPosition> position = repository.findByCustomerIdAndProductId(customerId, productId);
    position.ifPresent(repository::delete);

    return mapper.toShoppingCartPositionDto(position.orElseThrow(() -> new ResourceNotFoundException("Shopping cart position id=" + position + " could not be found")));
  }
}

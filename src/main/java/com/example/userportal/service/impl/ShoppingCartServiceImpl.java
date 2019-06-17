package com.example.userportal.service.impl;

import com.example.userportal.domain.Customer;
import com.example.userportal.domain.Product;
import com.example.userportal.domain.ShoppingCartPosition;
import com.example.userportal.exception.ResourceNotFoundException;
import com.example.userportal.repository.CustomerRepository;
import com.example.userportal.repository.ProductRepository;
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
  private final ShoppingCartRepository shoppingCartRepository;
  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;
  private final ShoppingCartPositionMapper mapper;

  @Override
  public ShoppingCartPositionDTO addPosition(int productId) {
    Integer customerId = SecurityUtils.getCurrentUserId();
    Customer customer = findCustomerById(customerId);
    Product product = findProductById(productId);
    Optional<ShoppingCartPosition> position = shoppingCartRepository.findByCustomerAndProduct(customer, product);
    return position
            .map(p -> {
              p.setQuantity(p.getQuantity() + 1);
              return mapper.toShoppingCartPositionDto(shoppingCartRepository.save(p));
            })
            .orElseGet(() -> {
              ShoppingCartPosition newPosition = ShoppingCartPosition.builder()
                      .customer(customer)
                      .product(product)
                      .quantity(1)
                      .build();
              return mapper.toShoppingCartPositionDto(shoppingCartRepository.save(newPosition));
            });
  }

  @Override
  public ShoppingCartPositionDTO getPosition(int positionId) {
    ShoppingCartPosition position = shoppingCartRepository.findById(positionId)
            .orElseThrow(() -> new ResourceNotFoundException("Shopping cart position id=" + positionId + " could not be found"));
    return mapper.toShoppingCartPositionDto(position);
  }

  @Override
  public List<ShoppingCartPositionDTO> getAllCurrentCustomerPositions() {
    Integer customerId = SecurityUtils.getCurrentUserId();
    return mapper.toShoppingCartPositionDtos(Lists.newArrayList(shoppingCartRepository.findByCustomerId(customerId)));
  }

  @Override
  public ShoppingCartPositionDTO getPosition(int customerId, int productId) {
    Optional<ShoppingCartPosition> position = shoppingCartRepository.findByCustomerIdAndProductId(customerId, productId);

    return mapper.toShoppingCartPositionDto(position.orElseThrow(() -> new ResourceNotFoundException("Shopping cart position id=" + position + " could not be found")));
  }

  @Override
  public ShoppingCartPositionDTO updatePositionQuantity(int productId, int quantity) {
    Integer customerId = SecurityUtils.getCurrentUserId();
    Optional<ShoppingCartPosition> position = shoppingCartRepository.findByCustomerIdAndProductId(customerId, productId);
    position.ifPresent(p -> {
              p.setQuantity(quantity);
              shoppingCartRepository.save(p);
            }
    );

    return mapper.toShoppingCartPositionDto(position.orElseThrow(() -> new ResourceNotFoundException("Shopping cart position id=" + position + " could not be found")));
  }

  @Override
  public ShoppingCartPositionDTO deletePosition(int productId) {
    Integer customerId = SecurityUtils.getCurrentUserId();
    Optional<ShoppingCartPosition> position = shoppingCartRepository.findByCustomerIdAndProductId(customerId, productId);
    position.ifPresent(shoppingCartRepository::delete);

    return mapper.toShoppingCartPositionDto(position.orElseThrow(() -> new ResourceNotFoundException("Shopping cart position id=" + position + " could not be found")));
  }

  private Customer findCustomerById(Integer customerId) {
    return customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer with given id " + customerId + " does not exist"));
  }

  private Product findProductById(int productId) {
    return productRepository.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product with given id " + productId + " does not exist"));
  }
}

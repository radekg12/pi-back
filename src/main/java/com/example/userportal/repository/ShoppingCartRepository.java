package com.example.userportal.repository;

import com.example.userportal.domain.Customer;
import com.example.userportal.domain.Product;
import com.example.userportal.domain.ShoppingCartPosition;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCartPosition, Integer> {

  Optional<ShoppingCartPosition> findById(int id);

  Iterable<ShoppingCartPosition> findByCustomerByCustomerId(Customer customerByCustomerId);

  Iterable<ShoppingCartPosition> findByCustomerId(int customerId);

  Optional<ShoppingCartPosition> findByCustomerByCustomerIdAndProductByProductId(Customer customerByCustomerId, Product productByProductId);

  Optional<ShoppingCartPosition> findByCustomerIdAndProductId(int customerId, int productId);

//  void deleteShoppingCartPositionByCustomerByCustomerIdAndProductByProductId(int customerId, int productId);

  void deleteAllByCustomerId(int customerId);

}

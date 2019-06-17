package com.example.userportal.repository;

import com.example.userportal.domain.Customer;
import com.example.userportal.domain.Product;
import com.example.userportal.domain.ShoppingCartPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartPosition, Integer> {

  Optional<ShoppingCartPosition> findById(int id);

  Iterable<ShoppingCartPosition> findByCustomer(Customer customer);

  Iterable<ShoppingCartPosition> findByCustomerId(int customerId);

  Optional<ShoppingCartPosition> findByCustomerIdAndProductId(int customerId, int productId);

  Optional<ShoppingCartPosition> findByCustomerAndProduct(Customer customer, Product product);

  void deleteAllByCustomerId(int customerId);

}

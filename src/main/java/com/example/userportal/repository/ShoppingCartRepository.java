package com.example.userportal.repository;

import com.example.userportal.domain.Customer;
import com.example.userportal.domain.Product;
import com.example.userportal.domain.ShoppingCartPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartPosition, Integer> {

  Optional<ShoppingCartPosition> findById(int id);

  Iterable<ShoppingCartPosition> findByCustomer(Customer customer);

  Iterable<ShoppingCartPosition> findByCustomerId(int customerId);

  @Query("SELECT c.shoppingCartPositions FROM Customer c WHERE c.email=:username")
  List<ShoppingCartPosition> findByUsername(@Param("username") String username);

  Optional<ShoppingCartPosition> findByCustomerIdAndProductId(int customerId, int productId);

  Optional<ShoppingCartPosition> findByCustomerAndProduct(Customer customer, Product product);

  void deleteAllByCustomerId(int customerId);

}

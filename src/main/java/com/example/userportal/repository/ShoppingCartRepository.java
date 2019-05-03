package com.example.userportal.repository;

import com.example.userportal.domain.Customer;
import com.example.userportal.domain.Product;
import com.example.userportal.domain.ShoppingCartPosition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCartPosition, Integer> {

  Optional<ShoppingCartPosition> findById(int id);

  Iterable<ShoppingCartPosition> findByCustomerByCustomerId(Customer customerByCustomerId);

  Iterable<ShoppingCartPosition> findByCustomerId(int customerId);

  @Query("SELECT c.shoppingCartPositionsById FROM Customer c WHERE c.email=:username")
  Iterable<ShoppingCartPosition> findByUsername(@Param("username") String username);

  Optional<ShoppingCartPosition> findByCustomerByCustomerIdAndProductByProductId(Customer customerByCustomerId, Product productByProductId);

  Optional<ShoppingCartPosition> findByCustomerIdAndProductId(int customerId, int productId);

//  void deleteShoppingCartPositionByCustomerByCustomerIdAndProductByProductId(int customerId, int productId);

  void deleteAllByCustomerId(int customerId);

}

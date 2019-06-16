package com.example.userportal.repository;

import com.example.userportal.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  @Query("SELECT o.customer FROM Order o WHERE o.id=:orderId")
  Customer findCustomerByOrderId(@Param("orderId") int orderId);

  Optional<Customer> findOneByEmailIgnoreCase(String email);

  Optional<Customer> findOneWithAuthoritiesById(Integer id);

  Optional<Customer> findOneWithAuthoritiesByEmail(String email);

  boolean existsByEmail(String email);
}

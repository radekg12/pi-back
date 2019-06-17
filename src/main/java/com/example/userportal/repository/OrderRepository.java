package com.example.userportal.repository;

import com.example.userportal.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  @Query("SELECT o FROM Order o WHERE o.customer.id=:customerId ORDER BY o.dateOfOrder DESC")
  List<Order> findAllByCustomerId(@Param("customerId") int customerId);
}

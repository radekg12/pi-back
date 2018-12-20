package com.example.userportal.repository;

import com.example.userportal.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, Integer> {

  @Query("SELECT o FROM Order o WHERE o.customerByCustomerId.id=:customerId")
  Iterable<Order> findAllByCustomerId(@Param("customerId") int customerId);


}

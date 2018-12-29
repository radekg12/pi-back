package com.example.userportal.repository;

import com.example.userportal.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

  @Query("SELECT o.customerByCustomerId FROM Order o WHERE o.id=:orderId")
  Customer findCustomerByOrderId(@Param("orderId") int orderId);
}

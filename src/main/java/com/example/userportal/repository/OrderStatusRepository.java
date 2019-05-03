package com.example.userportal.repository;

import com.example.userportal.domain.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Integer> {
}

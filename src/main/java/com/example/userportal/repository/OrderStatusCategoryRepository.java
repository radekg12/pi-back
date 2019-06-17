package com.example.userportal.repository;

import com.example.userportal.domain.OrderStatusCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusCategoryRepository extends JpaRepository<OrderStatusCategory, Integer> {

}

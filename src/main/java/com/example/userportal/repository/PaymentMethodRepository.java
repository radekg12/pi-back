package com.example.userportal.repository;

import com.example.userportal.domain.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Integer> {
}

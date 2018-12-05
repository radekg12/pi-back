package com.example.userportal.service.impl;

import com.example.userportal.domain.Customer;
import com.example.userportal.repository.CustomerRepository;
import com.example.userportal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository repository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository repository) {
    this.repository = repository;
  }


  @Override
  public Customer getCustomer(int id) {
    return repository.findById(id).orElse(null);
  }

}

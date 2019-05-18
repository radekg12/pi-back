package com.example.userportal.service;

import com.example.userportal.domain.Customer;
import com.example.userportal.service.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

  Customer getCustomer(int id);

  CustomerDTO getCustomerDTO(int id);

  CustomerDTO getCurrentCustomerDTO();

  List<CustomerDTO> getCustomerDTOs();

  CustomerDTO getCustomerByOrder(int orderId);

  CustomerDTO updateCustomer(CustomerDTO customerDTO);

  Customer updateCustomer(Customer customer);

  Optional<Customer> findOneByEmail(String email);

  boolean existsByEmail(String email);
}

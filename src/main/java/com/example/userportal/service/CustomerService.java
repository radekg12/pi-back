package com.example.userportal.service;

import com.example.userportal.domain.Customer;
import com.example.userportal.service.dto.CustomerDTO;

public interface CustomerService {

  Customer getCustomer(int id);

  CustomerDTO getCustomerDTO(int id);

  Iterable<CustomerDTO> getCustomerDTOs();

  CustomerDTO getCustomerByOrder(int orderId);

  CustomerDTO saveCustomer(CustomerDTO customerDTO);
}

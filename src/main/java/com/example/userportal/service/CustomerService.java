package com.example.userportal.service;

import com.example.userportal.domain.Customer;
import com.example.userportal.service.dto.CustomerDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

  Customer getCustomer(int id);

  CustomerDTO getCustomerDTO(int id);

  CustomerDTO getCurrentCustomerDTO();

  List<CustomerDTO> getCustomerDTOs();

  CustomerDTO getCustomerByOrder(int orderId);

  CustomerDTO updateCustomer(CustomerDTO customerDTO);

  @Transactional
  CustomerDTO updateCurrentCustomer(CustomerDTO customerDTO);
}

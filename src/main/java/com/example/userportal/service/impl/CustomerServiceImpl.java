package com.example.userportal.service.impl;

import com.example.userportal.domain.Customer;
import com.example.userportal.exception.ResourceNotFoundException;
import com.example.userportal.exception.UnauthorizedCurrentUserException;
import com.example.userportal.repository.CustomerRepository;
import com.example.userportal.security.SecurityUtils;
import com.example.userportal.service.CustomerService;
import com.example.userportal.service.dto.AddressDTO;
import com.example.userportal.service.dto.CustomerDTO;
import com.example.userportal.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository repository;
  private final CustomerMapper mapper;

  @Override
  public Customer getCustomer(int id) {
    return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer id=" + id + " could not be found"));
  }

  @Override
  public CustomerDTO getCustomerDTO(int id) {
    Customer customer = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer id=" + id + " could not be found"));
    return mapper.toCustomerDto(customer);
  }

  @Override
  public CustomerDTO getCurrentCustomerDTO() {
    return getCustomerDTO(SecurityUtils.getCurrentUserId());
  }

  @Override
  public List<CustomerDTO> getCustomerDTOs() {
    List<Customer> customers = repository.findAll();
    return mapper.toCustomerDtos(customers);
  }

  @Override
  public CustomerDTO getCustomerByOrder(int orderId) {
    Customer customer = repository.findCustomerByOrderId(orderId);
    return mapper.toCustomerDto(customer);
  }

  @Transactional
  @Override
  public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
    Customer customer = findByEmail(customerDTO.getEmail());
    customer.setFirstName(customerDTO.getFirstName())
            .setLastName(customerDTO.getLastName())
            .setEmail(customerDTO.getEmail())
            .setPhoneNumber(customerDTO.getPhoneNumber());
    AddressDTO addressDTO = customerDTO.getAddress();
    customer.getAddress()
            .setStreet(addressDTO.getStreet())
            .setPostcode(addressDTO.getPostcode())
            .setCity(addressDTO.getCity());
    return mapper.toCustomerDto(customer);
  }

  @Transactional
  @Override
  public CustomerDTO updateCurrentCustomer(CustomerDTO customerDTO) {
    CustomerDTO currentCustomerDTO = getCurrentCustomerDTO();
    if (!currentCustomerDTO.getId().equals(customerDTO.getId()))
      throw new UnauthorizedCurrentUserException();
    return updateCustomer(customerDTO);
  }

  private Customer findByEmail(String email) {
    return repository.findOneByEmailIgnoreCase(email)
            .orElseThrow(() -> new ResourceNotFoundException("Customer with mail " + email + " does not exist"));
  }

}

package com.example.userportal.service.impl;

import com.example.userportal.domain.Customer;
import com.example.userportal.exception.ResourceNotFoundException;
import com.example.userportal.repository.CustomerRepository;
import com.example.userportal.security.SecurityUtils;
import com.example.userportal.service.CustomerService;
import com.example.userportal.service.dto.AddressDTO;
import com.example.userportal.service.dto.CustomerDTO;
import com.example.userportal.service.mapper.CustomerMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    Iterable<Customer> customers = repository.findAll();
    return mapper.toCustomerDtos(Lists.newArrayList(customers));
  }

  @Override
  public CustomerDTO getCustomerByOrder(int orderId) {
    Customer customer = repository.findCustomerByOrderId(orderId);
    return mapper.toCustomerDto(customer);
  }

  @Override
  public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
    return Optional.of(repository
            .findOneByEmailIgnoreCase(customerDTO.getEmail()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(customer -> {
              customer
                      .setFirstName(customerDTO.getFirstName())
                      .setLastName(customerDTO.getLastName())
                      .setEmail(customerDTO.getEmail())
                      .setPhoneNumber(customerDTO.getPhoneNumber());

              AddressDTO addressDTO = customerDTO.getAddress();
              customer.getAddressByAddressId()
                      .setStreet(addressDTO.getStreet())
                      .setPostcode(addressDTO.getPostcode())
                      .setCity(addressDTO.getCity());

              return repository.save(customer);
            })
            .map(mapper::toCustomerDto)
            .orElse(customerDTO);
  }

  @Override
  public Customer updateCustomer(Customer customer) {
    return repository.save(customer);
  }

  @Override
  public Optional<Customer> findOneByEmail(String email) {
    return repository.findOneByEmailIgnoreCase(email);
  }

  @Override
  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }

}

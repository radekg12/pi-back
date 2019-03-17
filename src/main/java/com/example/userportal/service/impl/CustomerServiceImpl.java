package com.example.userportal.service.impl;

import com.example.userportal.domain.Customer;
import com.example.userportal.domain.UserAccount;
import com.example.userportal.repository.CustomerRepository;
import com.example.userportal.repository.UserAccountRepository;
import com.example.userportal.service.CustomerService;
import com.example.userportal.service.dto.CustomerDTO;
import com.example.userportal.service.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository repository;
  private final UserAccountRepository userAccountRepository;
  private final CustomerMapper mapper;

  @Autowired
  public CustomerServiceImpl(CustomerRepository repository, UserAccountRepository userAccountRepository, CustomerMapper mapper) {
    this.repository = repository;
    this.userAccountRepository = userAccountRepository;
    this.mapper = mapper;
  }


  @Override
  public Customer getCustomer(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public CustomerDTO getCustomerDTO(int id) {
    Customer customer = repository.findById(id).orElse(null);
    return mapper.toCustomerDto(customer);
  }

  @Override
  public Iterable<CustomerDTO> getCustomerDTOs() {
    Iterable<Customer> customers = repository.findAll();
    return mapper.toCustomerDtos(customers);
  }

  @Override
  public CustomerDTO getCustomerByOrder(int orderId) {
    Customer customer = repository.findCustomerByOrderId(orderId);
    return mapper.toCustomerDto(customer);
  }

  @Override
  public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
    Customer customer = mapper.toCustomer(customerDTO);
    UserAccount account = userAccountRepository.findUserAccountByCustomerById(customer);
    customer.setUserAccountByUserAccountId(account);
    Customer saveCustomer = repository.save(customer);
    return mapper.toCustomerDto(saveCustomer);
  }

  @Override
  public Customer saveCustomer(Customer customer) {
    return repository.save(customer);
  }

}

package com.example.userportal.controller;


import com.example.userportal.service.CustomerService;
import com.example.userportal.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/customer"})
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @GetMapping
  public CustomerDTO getCustomer() {
    return customerService.getCurrentCustomerDTO();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(path = "/all")
  public List<CustomerDTO> getCustomers() {
    return customerService.getCustomerDTOs();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(path = "/{id}")
  public CustomerDTO getCustomer(@PathVariable("id") int id) {
    return customerService.getCustomerDTO(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(path = "/byOrderId/{id}")
  public CustomerDTO getCustomers(@PathVariable("id") int orderId) {
    return customerService.getCustomerByOrder(orderId);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @PutMapping
  public CustomerDTO updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
    return customerService.updateCustomer(customerDTO);
  }

}

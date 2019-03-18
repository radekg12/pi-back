package com.example.userportal.controller;


import com.example.userportal.configuration.UserPrincipal;
import com.example.userportal.service.CustomerService;
import com.example.userportal.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/customer"})
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public CustomerDTO getCustomer(Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getCustomerId();
    return customerService.getCustomerDTO(customerId);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(path = "/all")
  public Iterable<CustomerDTO> getCustomers() {
    return customerService.getCustomerDTOs();
  }

  @GetMapping(path = "/{id}")
  public CustomerDTO getCustomer(@PathVariable("id") int id) {
    return customerService.getCustomerDTO(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(path = "/byOrderId/{id}")
  public CustomerDTO getCustomers(@PathVariable("id") int orderId) {
    return customerService.getCustomerByOrder(orderId);
  }

  @PutMapping
  public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
    return customerService.saveCustomer(customerDTO);
  }

}

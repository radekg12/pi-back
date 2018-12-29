package com.example.userportal.controller;


import com.example.userportal.service.CustomerService;
import com.example.userportal.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
  public CustomerDTO getCustomer() {
    int customerId = 1;
    return customerService.getCustomerDTO(customerId);
  }

  @GetMapping(path = "/{id}")
  public CustomerDTO getCustomer(@PathVariable("id") int id) {
    return customerService.getCustomerDTO(id);
  }


  @GetMapping(path = "/all")
  public Iterable<CustomerDTO> getCustomers() {
    return customerService.getCustomerDTOs();
  }

  @GetMapping(path = "/byOrderId/{id}")
  public CustomerDTO getCustomers(@PathVariable("id") int orderId) {
    return customerService.getCustomerByOrder(orderId);
  }

  @PutMapping
  public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
    return customerService.saveCustomer(customerDTO);
  }

}

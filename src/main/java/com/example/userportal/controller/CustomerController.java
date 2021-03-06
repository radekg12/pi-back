package com.example.userportal.controller;

import com.example.userportal.service.CustomerService;
import com.example.userportal.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomerDTOs();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") int id) {
        return customerService.getCustomerDTO(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/order/{id}")
    public CustomerDTO getCustomersByOrder(@PathVariable("id") int orderId) {
        return customerService.getCustomerByOrder(orderId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping
    public CustomerDTO updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerDTO);
    }
}

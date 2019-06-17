package com.example.userportal.controller;

import com.example.userportal.service.CustomerService;
import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.CustomerDTO;
import com.example.userportal.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public AccountController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("my-data")
    public CustomerDTO getMyData() {
        return customerService.getCurrentCustomerDTO();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/orders")
    public List<OrderDTO> getMyOrders() {
        return orderService.findAllCurrentCustomerOrders();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("my-data")
    public CustomerDTO updateMyData(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCurrentCustomer(customerDTO);
    }
}

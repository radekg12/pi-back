package com.example.userportal.controller;

import com.example.userportal.service.OrderService;
import com.example.userportal.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER')")
    @GetMapping
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @GetMapping(path = "/{id}")
    public OrderDTO findById(@PathVariable("id") int id) {
        return orderService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/customer/{id}")
    public List<OrderDTO> findAllByCustomerId(@PathVariable("id") int customerId) {
        return orderService.findAllByCustomerId(customerId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER')")
    @PutMapping("/{orderId}")
    public OrderDTO changeOrderStatus(@PathVariable("orderId") int orderId,
                                      @RequestParam("statusId") int statusId) {
        return orderService.updateOrderStatus(orderId, statusId);
    }
}

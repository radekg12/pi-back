package com.example.userportal.controller;

import com.example.userportal.requestmodel.WarehouseStateUpdateRequest;
import com.example.userportal.service.ProductService;
import com.example.userportal.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping({"/warehouse"})
public class WarehouseController {
  private final ProductService productService;

  @Autowired
  public WarehouseController(ProductService productService) {
    this.productService = productService;
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER')")
  @PutMapping({"/pick-up"})
  public ProductDTO putProductIntoWarehouse(@Valid @RequestBody WarehouseStateUpdateRequest request) {
    return productService.putProductIntoWarehouse(request);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER')")
  @PutMapping({"/delivery"})
  public ProductDTO takeProductFromWarehouse(@Valid @RequestBody WarehouseStateUpdateRequest request) {
    return productService.takeProductFromWarehouse(request);
  }
}

package com.example.userportal.controller;

import com.example.userportal.requestmodel.UpdateShoppingCartRequest;
import com.example.userportal.service.ShoppingCartService;
import com.example.userportal.service.dto.ShoppingCartPositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/shopping-carts"})
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  @Autowired
  public ShoppingCartController(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping
  public ShoppingCartPositionDTO addPosition(@Valid @RequestBody int productId) {
    return shoppingCartService.addPosition(productId);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @DeleteMapping(path = "/{productId}")
  public ShoppingCartPositionDTO deletePosition(@PathVariable("productId") int productId) {
    return shoppingCartService.deletePosition(productId);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PutMapping
  public ShoppingCartPositionDTO updatePosition(@Valid @RequestBody UpdateShoppingCartRequest request) {
    return shoppingCartService.updatePositionQuantity(request.getProductId(), request.getQuantity());
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping
  public List<ShoppingCartPositionDTO> findAllPositions() {
    return shoppingCartService.getAllCurrentCustomerPositions();
  }
}

package com.example.userportal.controller;

import com.example.userportal.requestmodel.UpdateShoppingCartModel;
import com.example.userportal.service.ShoppingCartService;
import com.example.userportal.service.dto.ShoppingCartPositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/shoppingCart"})
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  @Autowired
  public ShoppingCartController(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PostMapping
  public ShoppingCartPositionDTO add(@Valid @RequestBody int productId) {
    return shoppingCartService.addPosition(productId);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @DeleteMapping(path = "/{id}")
  public ShoppingCartPositionDTO delete(@PathVariable("id") int productId) {
    return shoppingCartService.deletePosition(productId);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PutMapping
  public ShoppingCartPositionDTO update(@Valid @RequestBody UpdateShoppingCartModel body) {
    return shoppingCartService.updatePositionQuantity(body.getProductId(), body.getQuantity());
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping
  public List<ShoppingCartPositionDTO> findAll() {
    return shoppingCartService.getAllCurrentCustomerPositions();
  }

}

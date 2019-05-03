package com.example.userportal.controller;

import com.example.userportal.requestmodel.UpdateShoppingCartModel;
import com.example.userportal.security.jwt.UserPrincipal;
import com.example.userportal.service.ShoppingCartService;
import com.example.userportal.service.dto.ShoppingCartPositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({"/shoppingCart"})
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  @Autowired
  public ShoppingCartController(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
  }

  @PreAuthorize("hasRole('USER')")
  @PostMapping
  public ShoppingCartPositionDTO add(@Valid @RequestBody int productId, Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getId();
    return shoppingCartService.addPosition(customerId, productId);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @DeleteMapping(path = "/{id}")
  public ShoppingCartPositionDTO delete(@PathVariable("id") int productId, Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getId();
    return shoppingCartService.deletePosition(customerId, productId);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @PutMapping
  public ShoppingCartPositionDTO update(@Valid @RequestBody UpdateShoppingCartModel body, Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getId();
    return shoppingCartService.updatePositionQuantity(customerId, body.getProductId(), body.getQuantity());
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping
  public Iterable<ShoppingCartPositionDTO> findAll(Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getId();
    return shoppingCartService.getAllPositions(customerId);
  }

}

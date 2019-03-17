package com.example.userportal.controller;

import com.example.userportal.RequestModel.UpdateShoppingCartModel;
import com.example.userportal.configuration.UserPrincipal;
import com.example.userportal.service.ShoppingCartService;
import com.example.userportal.service.dto.ShoppingCartPositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/shoppingCart"})
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  @Autowired
  public ShoppingCartController(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
  }

  @PostMapping
  public ShoppingCartPositionDTO add(@RequestBody int productId, Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getCustomerId();
    return shoppingCartService.addPosition(customerId, productId);
  }


  @DeleteMapping(path = "/{id}")
  public ShoppingCartPositionDTO delete(@PathVariable("id") int productId, Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getCustomerId();
    return shoppingCartService.deletePosition(customerId, productId);
  }

  @PutMapping
  public ShoppingCartPositionDTO update(@RequestBody UpdateShoppingCartModel body, Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getCustomerId();
    return shoppingCartService.updatePositionQuantity(customerId, body.getProductId(), body.getQuantity());
  }

  @GetMapping
  public Iterable<ShoppingCartPositionDTO> findAll(Authentication authentication) {
    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
    int customerId = principal.getCustomerId();
    return shoppingCartService.getAllPositions(customerId);
  }

}

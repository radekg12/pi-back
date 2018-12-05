package com.example.userportal.controller;

import com.example.userportal.RequestModel.UpdateShoppingCartModel;
import com.example.userportal.domain.ShoppingCartPosition;
import com.example.userportal.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.55:4200", "http://192.168.0.55:8081", "http://localhost:8081"}, maxAge = 3600)
@RestController
//@RequestMapping({"/api"})
@RequestMapping({"/shoppingCart"})
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  @Autowired
  public ShoppingCartController(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
  }

  @PostMapping
  public ShoppingCartPosition add(@RequestBody int productId) {
    return shoppingCartService.addPosition(1, productId);
  }


  @DeleteMapping(path = "/{id}")
  public ShoppingCartPosition delete(@PathVariable("id") int productId) {
    return shoppingCartService.deletePosition(1, productId);
  }

  @PutMapping
  public ShoppingCartPosition update(@RequestBody UpdateShoppingCartModel body) {
    return shoppingCartService.updatePositionQuantity(1, body.getProductId(), body.getQuantity());
  }

  @GetMapping
  public Iterable<ShoppingCartPosition> findAll() {
    return shoppingCartService.getAllPositions(1);
  }

}

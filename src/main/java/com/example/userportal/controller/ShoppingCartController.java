package com.example.userportal.controller;

import com.example.userportal.RequestModel.UpdateShoppingCartModel;
import com.example.userportal.service.ShoppingCartService;
import com.example.userportal.service.dto.ShoppingCartPositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
  public ShoppingCartPositionDTO add(@RequestBody int productId) {
    return shoppingCartService.addPosition(1, productId);
  }


  @DeleteMapping(path = "/{id}")
  public ShoppingCartPositionDTO delete(@PathVariable("id") int productId) {
    return shoppingCartService.deletePosition(1, productId);
  }

  @PutMapping
  public ShoppingCartPositionDTO update(@RequestBody UpdateShoppingCartModel body) {
    return shoppingCartService.updatePositionQuantity(1, body.getProductId(), body.getQuantity());
  }

  @GetMapping
  public Iterable<ShoppingCartPositionDTO> findAll() {
    return shoppingCartService.getAllPositions(1);
  }

}

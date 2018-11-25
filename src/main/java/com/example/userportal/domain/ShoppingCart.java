package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "shopping_cart", schema = "testdb")
public class ShoppingCart {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToMany(mappedBy = "shoppingCartByShoppingCartId")
  private Collection<Customer> customersById;

  @OneToMany(mappedBy = "shoppingCartByShoppingCartId")
  private Collection<ShoppingCartPosition> shoppingCartPositionsById;
}

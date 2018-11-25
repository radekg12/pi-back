package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shopping_cart_position", schema = "testdb")
public class ShoppingCartPosition {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
  private Product productByProductId;

  @ManyToOne
  @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id", nullable = false)
  private ShoppingCart shoppingCartByShoppingCartId;
}

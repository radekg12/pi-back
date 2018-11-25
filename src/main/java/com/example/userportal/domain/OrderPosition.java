package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_position", schema = "testdb")
public class OrderPosition {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "unity_price")
  private int unityPrice;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
  private Product productByProductId;

  @ManyToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
  private Order orderByOrderId;

}

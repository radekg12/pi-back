package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Builder
@Accessors(chain = true)
@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shopping_cart_position", schema = "testdb")
@ToString
public class ShoppingCartPosition {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @Column(name = "product_id", nullable = false)
//  @Column(name = "product_id", nullable = false)
  private int productId;

  @Column(name = "customer_id", nullable = false)
//  @Column(name = "customer_id", nullable = false)
  private int customerId;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Product productByProductId;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Customer customerByCustomerId;
}

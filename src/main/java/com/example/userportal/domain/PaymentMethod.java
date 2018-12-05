package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "payment_method", schema = "testdb")
public class PaymentMethod {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private int price;

  @JsonIgnore
  @OneToMany(mappedBy = "paymentMethodByPaymentMethodId")
  private Collection<Order> ordersById;
}

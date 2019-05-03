package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "payment_method", schema = "testdb")
public class PaymentMethod implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private Integer price;

  @JsonIgnore
  @OneToMany(mappedBy = "paymentMethodByPaymentMethodId")
  private Collection<Order> ordersById;
}

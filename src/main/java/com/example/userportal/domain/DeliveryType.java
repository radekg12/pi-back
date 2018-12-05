package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "delivery_type", schema = "testdb")
public class DeliveryType {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private int price;

  @JsonIgnore
  @OneToMany(mappedBy = "deliveryTypeByDeliveryTypeId")
  private Collection<Order> ordersById;
}

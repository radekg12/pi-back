package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "delivery_type", schema = "testdb")
public class DeliveryType implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private Integer price;

  @JsonIgnore
  @OneToMany(mappedBy = "deliveryTypeByDeliveryTypeId")
  private Collection<Order> ordersById;
}

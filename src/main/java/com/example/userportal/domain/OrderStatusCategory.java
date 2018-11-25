package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "order_status_category", schema = "testdb")
public class OrderStatusCategory {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "orderStatusCategoryByOrderStatusCategoryId")
  private Collection<OrderStatus> orderStatusesById;
}

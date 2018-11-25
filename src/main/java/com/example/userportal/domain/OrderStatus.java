package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "order_status", schema = "testdb")
public class OrderStatus {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "orderStatusByOrderStatusId")
  private Collection<Order> ordersById;

  @ManyToOne
  @JoinColumn(name = "order_status_category_id", referencedColumnName = "id", nullable = false)
  private OrderStatusCategory orderStatusCategoryByOrderStatusCategoryId;
}

package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "order_status", schema = "testdb")
public class OrderStatus implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "orderStatusByOrderStatusId")
  private Collection<Order> ordersById;

  @ManyToOne
  @JoinColumn(name = "order_status_category_id", referencedColumnName = "id", nullable = false)
  private OrderStatusCategory orderStatusCategoryByOrderStatusCategoryId;
}

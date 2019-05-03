package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "order_status_category", schema = "testdb")
public class OrderStatusCategory implements Serializable {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "orderStatusCategoryByOrderStatusCategoryId")
  private Collection<OrderStatus> orderStatusesById;
}

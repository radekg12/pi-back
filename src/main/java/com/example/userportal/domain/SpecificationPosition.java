package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "specification_position", schema = "testdb")
public class SpecificationPosition {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "value")
  private String value;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
  private Product productByProductId;
}

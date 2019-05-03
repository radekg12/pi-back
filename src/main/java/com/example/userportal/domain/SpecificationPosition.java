package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "specification_position", schema = "testdb")
public class SpecificationPosition implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "value")
  private String value;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
  private Product productByProductId;
}

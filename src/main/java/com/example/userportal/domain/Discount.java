package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Discount {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "percent_value")
  private int percentValue;
}

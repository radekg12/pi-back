package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
}

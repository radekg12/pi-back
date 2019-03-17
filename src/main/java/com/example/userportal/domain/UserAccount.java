package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_account", schema = "testdb")
public class UserAccount {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "username")
  private String username;

  @JsonIgnore
  @Column(name = "password")
  private String password;

  @JsonIgnore
  @OneToOne(mappedBy = "userAccountByUserAccountId")
  private Customer customerById;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_role_id", referencedColumnName = "id", nullable = false)
  private UserRole userRoleByUserRoleId;

}

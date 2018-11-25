package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "user_role", schema = "testdb")
public class UserRole {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "role_name")
  private String roleName;

  @OneToMany(mappedBy = "userRoleByUserRoleId")
  private Collection<UserAccount> userAccountsById;
}

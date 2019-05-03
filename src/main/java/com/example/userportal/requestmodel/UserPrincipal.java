package com.example.userportal.requestmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal {
  Integer id;
  String username;
  String password;
  String role;
  int customerId;
}

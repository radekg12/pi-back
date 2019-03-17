package com.example.userportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {

  private int id;
  private String username;
  private String password;
  private UserRoleDTO role;
  private CustomerDTO customer;

}

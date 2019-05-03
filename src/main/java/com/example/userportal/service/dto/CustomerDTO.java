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
public class CustomerDTO {
  private Integer id;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;
  private AddressDTO address;
}

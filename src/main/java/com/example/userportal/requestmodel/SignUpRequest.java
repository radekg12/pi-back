package com.example.userportal.requestmodel;

import com.example.userportal.service.dto.AddressDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {
  private Integer id;
  private String firstName;
  private String lastName;
  private Integer phoneNumber;
  private String email;
  private String password;
  private AddressDTO address;
}

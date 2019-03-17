package com.example.userportal.RequestModel;

import com.example.userportal.service.dto.AddressDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {
  private int id;
  private String companyName;
  private String firstName;
  private String lastName;
  private Integer phoneNumber;
  private String email;
  private String password;
  private AddressDTO address;
}

package com.example.userportal.requestmodel.payu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayMethod {
  String value;
  String type;
  String authorizationCode;
}

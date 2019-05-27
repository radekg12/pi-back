package com.example.userportal.requestmodel.payu;

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
public class GooglePayToken {
  private String signature;
  private String protocolVersion;
  private String signedMessage;
}

package com.example.userportal.requestmodel.payu;

import lombok.Data;

@Data
public class PayUOrderNotifyRequest {
  PayUOrder order;
  String localReceiptDateTime;
  PayUProperty[] properties;
}

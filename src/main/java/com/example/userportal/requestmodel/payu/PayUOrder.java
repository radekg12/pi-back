package com.example.userportal.requestmodel.payu;

import lombok.Data;

@Data
public class PayUOrder {

  String orderId;
  String extOrderId;
  String orderCreateDate;
  String notifyUrl;
  String customerIp;
  String merchantPosId;
  String description;
  String currencyCode;
  String totalAmount;
  Buyer buyer;
  PayMethod payMethod;
  PayUProduct[] products;
  PayUStatus status;
}

package com.example.userportal.RequestModel;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateShoppingCartModel implements Serializable {
  private int productId;
  private int quantity;
}

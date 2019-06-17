package com.example.userportal.requestmodel;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingCartUpdateRequest implements Serializable {
  private Integer productId;
  private Integer quantity;
}

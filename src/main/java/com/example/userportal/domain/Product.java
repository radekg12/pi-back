package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Product {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "company")
  private String company;

  @Column(name = "quantity_in_stock")
  private int quantityInStock;

  @Column(name = "unity_price")
  private int unityPrice;

  @Column(name = "image_url")
  private String imageUrl;

  @OneToMany(mappedBy = "productByProductId")
  private Collection<OrderPosition> orderPositionsById;

  @ManyToOne
  @JoinColumn(name = "subcategory_id", referencedColumnName = "id", nullable = false)
  private ProductSubcategory productSubcategoryBySubcategoryId;

  @OneToMany(mappedBy = "productByProductId")
  private Collection<ShoppingCartPosition> shoppingCartPositionsById;

  @OneToMany(mappedBy = "productByProductId")
  private Collection<SpecificationPosition> specificationPositionsById;

}

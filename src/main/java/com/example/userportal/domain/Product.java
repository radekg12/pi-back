package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Data
@Accessors(chain = true)
@Entity
public class Product {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "company")
  private String company;

  @Column(name = "quantity_in_stock")
  private int quantityInStock;

  @Column(name = "unit_price")
  private int unitPrice;

  @Column(name = "image_url", length = 500)
  private String imageUrl;

  @JsonIgnore
  @OneToMany(mappedBy = "productByProductId")
  private Collection<OrderPosition> orderPositions;

  @ManyToOne
  @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
  private ProductSubcategory productSubcategory;

  @JsonIgnore
  @OneToMany(mappedBy = "productByProductId")
  private Collection<ShoppingCartPosition> shoppingCartPositions;

  @OneToMany(mappedBy = "productByProductId")
  private Collection<SpecificationPosition> specificationPositions;

}

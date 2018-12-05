package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "product_category", schema = "testdb")
public class ProductCategory {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "category_name")
  private String name;

  @OneToMany(mappedBy = "productCategory")
  private Collection<ProductSubcategory> subcategories;
}

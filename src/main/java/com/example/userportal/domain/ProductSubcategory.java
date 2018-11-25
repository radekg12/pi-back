package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "product_subcategory", schema = "testdb")
public class ProductSubcategory {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "subcategory_name")
  private String subcategoryName;

  @OneToMany(mappedBy = "productSubcategoryBySubcategoryId")
  private Collection<Product> productsById;

  @ManyToOne
  @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
  private ProductCategory productCategoryByCategoryId;
}

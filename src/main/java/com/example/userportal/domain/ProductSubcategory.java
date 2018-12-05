package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "productSubcategory")
  private Collection<Product> products;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private ProductCategory productCategory;
}

package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Product extends AbstractAuditingEntity implements Serializable {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "company")
  private String company;

  @Column(name = "logical_quantity_in_stock")
  private Integer logicalQuantityInStock;

  @Column(name = "physical_quantity_in_stock")
  private Integer physicalQuantityInStock;

  @Column(name = "unit_price")
  private Integer unitPrice;

  @Column(name = "image_url", length = 500)
  private String imageUrl;

  @Column(name = "available", length = 500)
  private Boolean available = true;

  @JsonIgnore
  @OneToMany(mappedBy = "productByProductId")
  private Collection<OrderPosition> orderPositions;

  @ManyToOne
  @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
  private ProductSubcategory productSubcategory;

  @JsonIgnore
  @OneToMany(mappedBy = "productByProductId")
  private Collection<ShoppingCartPosition> shoppingCartPositions;

  @OneToMany(mappedBy = "productByProductId", cascade = CascadeType.ALL)
  private Collection<SpecificationPosition> specificationPositions;

}

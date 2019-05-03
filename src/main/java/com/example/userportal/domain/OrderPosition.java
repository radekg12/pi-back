package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Accessors(chain = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_position", schema = "testdb")
public class OrderPosition implements Serializable {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "unit_price")
  private Integer unitPrice;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
  private Product productByProductId;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
  private Order orderByOrderId;

}

package com.example.userportal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Builder
@Accessors(chain = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`", schema = "testdb")
public class Order implements Serializable {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "date_of_order")
  private Timestamp dateOfOrder;

  @Column(name = "date_of_delivery")
  private Timestamp dateOfDelivery;

  @Column(name = "total_amount")
  private int totalAmount;

  @ManyToOne
  @JoinColumn(name = "order_status_id", referencedColumnName = "id", nullable = false)
  private OrderStatus orderStatus;

  @ManyToOne
  @JoinColumn(name = "delivery_type_id", referencedColumnName = "id", nullable = false)
  private DeliveryType deliveryType;

  @ManyToOne
  @JoinColumn(name = "payment_method_id", referencedColumnName = "id", nullable = false)
  private PaymentMethod paymentMethod;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "delivery_address_id", referencedColumnName = "id", nullable = false)
  private Address address;

  @OneToMany(mappedBy = "order")
  private Collection<OrderPosition> orderPositions;

}

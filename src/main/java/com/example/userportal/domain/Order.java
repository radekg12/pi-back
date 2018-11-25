package com.example.userportal.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
public class Order {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "date_of_order")
  private Timestamp dateOfOrder;

  @Column(name = "date_of_delivery")
  private Timestamp dateOfDelivery;

  @Column(name = "total_amount")
  private int totalAmount;

  @ManyToOne
  @JoinColumn(name = "order_status_id", referencedColumnName = "id", nullable = false)
  private OrderStatus orderStatusByOrderStatusId;

  @ManyToOne
  @JoinColumn(name = "delivery_type_id", referencedColumnName = "id", nullable = false)
  private DeliveryType deliveryTypeByDeliveryTypeId;

  @ManyToOne
  @JoinColumn(name = "payment_method_id", referencedColumnName = "id", nullable = false)
  private PaymentMethod paymentMethodByPaymentMethodId;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
  private Customer customerByCustomerId;

  @ManyToOne
  @JoinColumn(name = "delivery_address_id", referencedColumnName = "id", nullable = false)
  private Address addressByDeliveryAddressId;

  @OneToMany(mappedBy = "orderByOrderId")
  private Collection<OrderPosition> orderPositionsById;

}

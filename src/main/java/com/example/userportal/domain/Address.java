package com.example.userportal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Address extends AbstractAuditingEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "postcode")
  private String postcode;

//  @JsonIgnore
//  @OneToMany(mappedBy = "addressByAddressId")
//  private Collection<Customer> customerById;
//
//  @JsonIgnore
//  @OneToMany(mappedBy = "addressByDeliveryAddressId")
//  private Collection<Order> ordersById;

}

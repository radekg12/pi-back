package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Address {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "postcode")
  private int postcode;

  @JsonIgnore
  @OneToMany(mappedBy = "addressByAddressId")
  private Collection<Customer> customersById;

  @JsonIgnore
  @OneToMany(mappedBy = "addressByDeliveryAddressId")
  private Collection<Order> ordersById;

}

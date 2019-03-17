package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Customer {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "company_name")
  private String companyName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "phone_number")
  private Integer phoneNumber;

  @NaturalId
  @Column(name = "email", unique = true)
  private String email;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
  private Address addressByAddressId;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "user_account_id", referencedColumnName = "id", nullable = false)
  private UserAccount userAccountByUserAccountId;

  @JsonIgnore
  @OneToMany(mappedBy = "customerByCustomerId")
  private Collection<Order> ordersById;

  @JsonIgnore
  @OneToMany(mappedBy = "customerByCustomerId")
  private Collection<ShoppingCartPosition> shoppingCartPositionsById;

}

package com.example.userportal.domain;

import lombok.Data;

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

  @Column(name = "email")
  private String email;


  @ManyToOne
  @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
  private Address addressByAddressId;

  @ManyToOne
  @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id", nullable = false)
  private ShoppingCart shoppingCartByShoppingCartId;

  @ManyToOne
  @JoinColumn(name = "user_account_id", referencedColumnName = "id", nullable = false)
  private UserAccount userAccountByUserAccountId;

  @OneToMany(mappedBy = "customerByCustomerId")
  private Collection<Order> ordersById;

}

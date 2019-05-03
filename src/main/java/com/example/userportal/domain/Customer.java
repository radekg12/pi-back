package com.example.userportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Customer extends AbstractAuditingEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NaturalId
  @Email
  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "password_hash")
  private String passwordHash;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "activated")
  private Boolean activated = true;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
  private Address addressByAddressId;

  @JsonIgnore
  @OneToMany(mappedBy = "customerByCustomerId")
  private Collection<Order> ordersById;

  @JsonIgnore
  @OneToMany(mappedBy = "customerByCustomerId")
  private Collection<ShoppingCartPosition> shoppingCartPositionsById;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
          name = "customer_authority",
          joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
          inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
  private Set<Authority> authorities = new HashSet<>();
}

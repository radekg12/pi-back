package com.example.userportal.configuration;

import com.example.userportal.domain.UserAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrincipal implements UserDetails {
  private Integer id;

  private String username;

  @JsonIgnore
  private String password;
  private Integer customerId;

  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Integer id, String username, String password, Integer customerId, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.customerId = customerId;
    this.authorities = authorities;
  }

  public static UserPrincipal create(UserAccount user) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getUserRoleByUserRoleId().getRoleName()));

    return new UserPrincipal(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getCustomerById() != null ? user.getCustomerById().getId() : null,
            authorities
    );
  }

  public Integer getId() {
    return id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserPrincipal that = (UserPrincipal) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}

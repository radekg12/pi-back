package com.example.userportal.security.jwt;

import com.example.userportal.domain.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserPrincipal implements UserDetails {
  private Integer id;
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Integer id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserPrincipal create(Customer customer) {
    Set<GrantedAuthority> authorities = new HashSet<>();
    customer.getAuthorities().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getRoleName())));

    return new UserPrincipal(
            customer.getId(),
            customer.getEmail(),
            customer.getPasswordHash(),
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

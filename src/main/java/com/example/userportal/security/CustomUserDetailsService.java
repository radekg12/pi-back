package com.example.userportal.security;

import com.example.userportal.domain.Customer;
import com.example.userportal.repository.CustomerRepository;
import com.example.userportal.security.jwt.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  final CustomerRepository customerRepository;

  @Transactional
  public UserDetails loadUserById(Integer id) {
    Customer customer = customerRepository.findOneWithAuthoritiesById(id).orElseThrow(
            () -> new UsernameNotFoundException("Not found user by id: " + id)
    );

    return UserPrincipal.create(customer);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer customer = customerRepository.findOneByEmailIgnoreCase(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("Not found user by username: " + username)
            );

    return UserPrincipal.create(customer);
  }
}

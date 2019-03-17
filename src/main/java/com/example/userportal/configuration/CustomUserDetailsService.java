package com.example.userportal.configuration;

import com.example.userportal.domain.UserAccount;
import com.example.userportal.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


  final UserAccountRepository userAccountRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username)
          throws UsernameNotFoundException {
    UserAccount user = userAccountRepository.findByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("Not found user by username: " + username)
            );

    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadUserById(Integer id) {
    UserAccount user = userAccountRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("Not found user by id: " + id)
    );

    return UserPrincipal.create(user);
  }
}

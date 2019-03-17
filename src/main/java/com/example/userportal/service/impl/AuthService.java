package com.example.userportal.service.impl;

import com.example.userportal.RequestModel.JwtAuthenticationResponse;
import com.example.userportal.RequestModel.SignInRequest;
import com.example.userportal.RequestModel.SignUpRequest;
import com.example.userportal.configuration.JwtTokenProvider;
import com.example.userportal.domain.Customer;
import com.example.userportal.domain.UserAccount;
import com.example.userportal.domain.UserRole;
import com.example.userportal.repository.UserAccountRepository;
import com.example.userportal.repository.UserRoleRepository;
import com.example.userportal.service.CustomerService;
import com.example.userportal.service.mapper.CustomerMapper;
import com.example.userportal.service.mapper.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final UserAccountRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;
  private final UserAccountMapper userAccountMapper;
  private final CustomerMapper customerMapper;
  private final CustomerService customerService;


  public JwtAuthenticationResponse authenticateUser(@Valid SignInRequest signInRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    signInRequest.getUsername(),
                    signInRequest.getPassword()
            )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    UserAccount userAccount = userRepository.findByUsername(signInRequest.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
    return new JwtAuthenticationResponse(jwt, userAccountMapper.toUserAccountDto(userAccount));
  }

  public boolean registerUser(@Valid SignUpRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getEmail())) {
      return false;
    }

    Customer customer = customerMapper.toCustomer(signUpRequest);

    customer.getUserAccountByUserAccountId().setPassword(passwordEncoder.encode(customer.getUserAccountByUserAccountId().getPassword()));

    UserRole userRole = userRoleRepository.findByRoleName("User")
            .orElseThrow(() -> new NotFoundException("Role not found"));

    customer.getUserAccountByUserAccountId().setUserRoleByUserRoleId(userRole);

    customerService.saveCustomer(customer);
    return true;
  }


}

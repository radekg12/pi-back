package com.example.userportal.service.impl;

import com.example.userportal.domain.Address;
import com.example.userportal.domain.Authority;
import com.example.userportal.domain.Customer;
import com.example.userportal.exception.EmailAlreadyUsedException;
import com.example.userportal.exception.EmailNotFoundException;
import com.example.userportal.exception.InvalidPasswordException;
import com.example.userportal.repository.AddressRepository;
import com.example.userportal.repository.AuthorityRepository;
import com.example.userportal.requestmodel.JwtAuthenticationResponse;
import com.example.userportal.requestmodel.SignInRequest;
import com.example.userportal.requestmodel.SignUpRequest;
import com.example.userportal.security.jwt.AuthoritiesConstants;
import com.example.userportal.security.jwt.JwtTokenProvider;
import com.example.userportal.service.AuthService;
import com.example.userportal.service.CustomerService;
import com.example.userportal.service.dto.CustomerDTO;
import com.example.userportal.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final AuthorityRepository authorityRepository;
  private final AddressRepository addressRepository;
  private final CustomerService customerService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;
  private final CustomerMapper customerMapper;


  @Override
  public JwtAuthenticationResponse authenticateUser(SignInRequest signInRequest) {

    if (!customerService.existsByEmail(signInRequest.getUsername())) {
      throw new EmailNotFoundException();
    }

    Set<Authority> authorities = customerService.findOneByEmail(signInRequest.getUsername()).map(Customer::getAuthorities).orElseThrow(InvalidPasswordException::new);

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    signInRequest.getUsername(),
                    signInRequest.getPassword()
            )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.createToken(authentication, signInRequest.getRememberMe());

    return new JwtAuthenticationResponse(jwt, signInRequest.getRememberMe(), authorities);
  }

  @Override
  @Transactional
  public CustomerDTO registerUser(SignUpRequest signUpRequest) {
    if (customerService.existsByEmail(signUpRequest.getEmail())) {
      throw new EmailAlreadyUsedException();
    }

    Customer customer = customerMapper.toCustomer(signUpRequest);
    customer.setPasswordHash(passwordEncoder.encode(customer.getPasswordHash()));
    if (customer.getAddressByAddressId() == null)
      customer.setAddressByAddressId(new Address()
              .setCity("")
              .setStreet("")
              .setPostcode("")
      );

    addressRepository.save(customer.getAddressByAddressId());

    Set<Authority> authorities = new HashSet<>();
    authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
    customer.setAuthorities(authorities);
    customerService.updateCustomer(customer);
    return customerMapper.toCustomerDto(customer);
  }
}

package com.example.userportal.service.impl;

import com.example.userportal.domain.Address;
import com.example.userportal.domain.Authority;
import com.example.userportal.domain.Customer;
import com.example.userportal.exception.EmailAlreadyUsedException;
import com.example.userportal.exception.EmailNotFoundException;
import com.example.userportal.exception.InvalidPasswordException;
import com.example.userportal.repository.AddressRepository;
import com.example.userportal.repository.AuthorityRepository;
import com.example.userportal.repository.CustomerRepository;
import com.example.userportal.requestmodel.JwtAuthenticationResponse;
import com.example.userportal.requestmodel.SignInRequest;
import com.example.userportal.requestmodel.SignUpRequest;
import com.example.userportal.security.jwt.AuthoritiesConstants;
import com.example.userportal.security.jwt.JwtTokenProvider;
import com.example.userportal.service.AuthService;
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
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final CustomerMapper customerMapper;

    @Override
    public JwtAuthenticationResponse authenticateUser(SignInRequest signInRequest) {
        if (!customerRepository.existsByEmail(signInRequest.getUsername())) {
            throw new EmailNotFoundException();
        }
        Set<Authority> authorities = getCustomerAuthorities(signInRequest.getUsername());
        Authentication authentication = getAuthenticationFromRequest(signInRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication, signInRequest.getRememberMe());
        return new JwtAuthenticationResponse(token, signInRequest.getRememberMe(), authorities);
    }

    @Override
    @Transactional
    public CustomerDTO registerUser(SignUpRequest signUpRequest) {
        if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyUsedException();
        }
        Customer customer = customerMapper.toCustomer(signUpRequest);
        customer.setPasswordHash(passwordEncoder.encode(customer.getPasswordHash()));
        if (customer.getAddressByAddressId() == null) {
            customer.setAddressByAddressId(createDefaultAddress());
        }
        addressRepository.save(customer.getAddressByAddressId());
        Set<Authority> authorities = getDefaultUserAuthorities();
        customer.setAuthorities(authorities);
        customerRepository.save(customer);
        return customerMapper.toCustomerDto(customer);
    }

    private Set<Authority> getCustomerAuthorities(String username) {
        return customerRepository
                .findOneByEmailIgnoreCase(username)
                .map(Customer::getAuthorities)
                .orElseThrow(InvalidPasswordException::new);
    }

    private Authentication getAuthenticationFromRequest(SignInRequest signInRequest) {
        String username = signInRequest.getUsername();
        String password = signInRequest.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(token);
    }

    private Address createDefaultAddress() {
        return new Address()
                .setCity("")
                .setStreet("")
                .setPostcode("");
    }

    private Set<Authority> getDefaultUserAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        authorityRepository
                .findById(AuthoritiesConstants.USER)
                .ifPresent(authorities::add);
        return authorities;
    }
}

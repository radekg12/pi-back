package com.example.userportal.service.impl;

import com.example.userportal.domain.Address;
import com.example.userportal.repository.AddressRepository;
import com.example.userportal.service.AddressService;
import com.example.userportal.service.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

  private final AddressRepository repository;
  private final AddressMapper mapper;

  @Autowired
  public AddressServiceImpl(AddressRepository repository, AddressMapper mapper) {

    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Address save(Address address) {
    return repository.save(address);
  }

}

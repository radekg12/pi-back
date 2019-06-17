package com.example.userportal.service.mapper;

import com.example.userportal.domain.Customer;
import com.example.userportal.requestmodel.SignUpRequest;
import com.example.userportal.service.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface CustomerMapper {

  @Mapping(target = "address", source = "address")
  CustomerDTO toCustomerDto(Customer customer);

  @Mapping(target = "orders", ignore = true)
  Customer toCustomer(CustomerDTO customerDTO);

  List<CustomerDTO> toCustomerDtos(List<Customer> customers);

  List<Customer> toCustomers(List<CustomerDTO> customerDTOS);

  @Mapping(target = "passwordHash", source = "password")
  @Mapping(target = "orders", ignore = true)
  @Mapping(target = "address", source = "address")
  Customer toCustomer(SignUpRequest signUpRequest);
}

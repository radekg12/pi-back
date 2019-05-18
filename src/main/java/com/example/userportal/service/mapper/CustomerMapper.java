package com.example.userportal.service.mapper;

import com.example.userportal.domain.Customer;
import com.example.userportal.requestmodel.SignUpRequest;
import com.example.userportal.service.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface CustomerMapper {

  CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

  @Mapping(target = "address", source = "addressByAddressId")
  CustomerDTO toCustomerDto(Customer customer);

  @Mapping(target = "ordersById", ignore = true)
  @Mapping(target = "shoppingCartPositionsById", ignore = true)
  Customer toCustomer(CustomerDTO customerDTO);

  List<CustomerDTO> toCustomerDtos(List<Customer> customers);

  List<Customer> toCustomers(List<CustomerDTO> customerDTOS);

  @Mapping(target = "passwordHash", source = "password")
  @Mapping(target = "shoppingCartPositionsById", ignore = true)
  @Mapping(target = "ordersById", ignore = true)
  @Mapping(target = "addressByAddressId", source = "address")
  Customer toCustomer(SignUpRequest signUpRequest);
}

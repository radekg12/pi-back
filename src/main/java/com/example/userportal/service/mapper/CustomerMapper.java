package com.example.userportal.service.mapper;

import com.example.userportal.domain.Customer;
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

  @Mapping(target = "addressByAddressId", source = "address")
  @Mapping(target = "ordersById", ignore = true)
  @Mapping(target = "shoppingCartPositionsById", ignore = true)
  @Mapping(target = "userAccountByUserAccountId", ignore = true)
  Customer toCustomer(CustomerDTO customerDTO);

  List<CustomerDTO> toCustomerDtos(List<Customer> customers);

  List<Customer> toCustomers(List<CustomerDTO> customerDTOS);

  Iterable<CustomerDTO> toCustomerDtos(Iterable<Customer> customers);

  Iterable<Customer> toCustomers(Iterable<CustomerDTO> customerDTOS);

}

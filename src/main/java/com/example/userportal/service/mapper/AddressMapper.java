package com.example.userportal.service.mapper;

import com.example.userportal.domain.Address;
import com.example.userportal.service.dto.AddressDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  AddressDTO toAddressDto(Address address);

  Address toAddress(AddressDTO addressDTO);

  List<AddressDTO> toAddressDtos(List<Address> addresses);

  List<Address> toAddresses(List<AddressDTO> addressDTOS);

}

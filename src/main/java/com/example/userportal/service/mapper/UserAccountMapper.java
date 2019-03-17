package com.example.userportal.service.mapper;

import com.example.userportal.domain.UserAccount;
import com.example.userportal.service.dto.UserAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserRoleMapper.class, CustomerMapper.class})
public interface UserAccountMapper {

  UserAccountMapper MAPPER = Mappers.getMapper(UserAccountMapper.class);

  @Mapping(target = "role", source = "userRoleByUserRoleId")
  @Mapping(target = "customer", source = "customerById")
  UserAccountDTO toUserAccountDto(UserAccount userAccount);

  @Mapping(target = "customerById", source = "customer")
  @Mapping(target = "userRoleByUserRoleId", source = "role")
  UserAccount toUserAccount(UserAccountDTO userAccountDTO);
}

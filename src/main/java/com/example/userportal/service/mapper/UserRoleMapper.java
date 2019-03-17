package com.example.userportal.service.mapper;

import com.example.userportal.domain.UserRole;
import com.example.userportal.service.dto.UserRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

  UserRoleMapper MAPPER = Mappers.getMapper(UserRoleMapper.class);

  @Mapping(target = "name", source = "roleName")
  UserRoleDTO toUserRoleDto(UserRole userRole);

  @Mapping(target = "roleName", source = "name")
  @Mapping(target = "userAccountsById", ignore = true)
  UserRole toUserRole(UserRoleDTO userRoleDTO);
}

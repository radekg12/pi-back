package com.example.userportal.repository;

import com.example.userportal.domain.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
  Optional<UserRole> findByRoleName(String roleName);
}

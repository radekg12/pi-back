package com.example.userportal.repository;

import com.example.userportal.domain.Customer;
import com.example.userportal.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

  UserAccount findUserAccountByCustomerById(Customer customer);

  boolean existsByUsername(String username);

  Optional<UserAccount> findByUsername(String username);
}

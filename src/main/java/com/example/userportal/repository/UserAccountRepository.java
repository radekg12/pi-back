package com.example.userportal.repository;

import com.example.userportal.domain.Customer;
import com.example.userportal.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

  UserAccount findUserAccountByCustomersById(Customer customer);

}

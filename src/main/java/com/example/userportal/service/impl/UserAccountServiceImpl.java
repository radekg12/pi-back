package com.example.userportal.service.impl;

import com.example.userportal.domain.UserAccount;
import com.example.userportal.repository.UserAccountRepository;
import com.example.userportal.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

  private final UserAccountRepository repository;


  @Autowired
  public UserAccountServiceImpl(UserAccountRepository repository) {
    this.repository = repository;
  }


  @Override
  public UserAccount getUserAccount(int accountId) {
    Optional<UserAccount> account = repository.findById(accountId);
    return account.orElse(null);
  }

}

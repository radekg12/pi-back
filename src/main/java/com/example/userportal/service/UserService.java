package com.example.userportal.service;

import com.example.userportal.domain.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
  User create(User user);

  Optional<User> findById(Long id);

  User update(User user);

  User delete(Long id);

  Iterable<User> findAll();

  Long getCollectionSize();

  Iterable<User> getPage(int page);

  Page<User> findPaginated(int page, int size, String sort);
}

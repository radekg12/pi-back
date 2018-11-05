package com.example.userportal.service.impl;

import com.example.userportal.domain.User;
import com.example.userportal.repository.UserRepository;
import com.example.userportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public User create(User user) {
    return repository.save(user);
  }

  @Override
  public User delete(Long id) {
    Optional<User> user = findById(id);
    user.ifPresent(repository::delete);
    return user.orElse(null);
  }

  @Override
  public Iterable<User> findAll() {
    return repository.findAll();
  }

  @Override
  public Long getCollectionSize() {
    return repository.count();
  }

  @Override
  public Iterable<User> getPage(int page) {
    return repository.findAll(PageRequest.of(page, 20, Sort.Direction.DESC, "id"));
  }

  @Override
  public Page<User> findPaginated(int page, int size, String sort) {
    String[] sortBy = sort.split("_");
    String sortProperty = sortBy[0];
    Sort.Direction sortDirection = sortBy[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    return repository.findAll(PageRequest.of(page, size, sortDirection, sortProperty));
  }

  @Override
  public Optional<User> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  public User update(User user) {
    return null;
  }
}

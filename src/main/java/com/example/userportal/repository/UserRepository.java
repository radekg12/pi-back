package com.example.userportal.repository;

import com.example.userportal.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}

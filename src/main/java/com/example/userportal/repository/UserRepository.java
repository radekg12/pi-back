package com.example.userportal.repository;

import com.example.userportal.domain.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Customer entity.
 */
@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findOneByEmailIgnoreCase(String email);

  Optional<Customer> findOneByEmail(String login);

  @EntityGraph(attributePaths = "authorities")
  Optional<Customer> findOneWithAuthoritiesById(Integer id);

  @EntityGraph(attributePaths = "authorities")
  Optional<Customer> findOneWithAuthoritiesByEmail(String email);

}

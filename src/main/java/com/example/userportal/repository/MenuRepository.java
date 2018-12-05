package com.example.userportal.repository;

import com.example.userportal.domain.ProductCategory;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<ProductCategory, Integer> {
}

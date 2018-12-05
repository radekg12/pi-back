package com.example.userportal.service;

import com.example.userportal.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
  Product create(Product product);

  Product findById(int id);

  Product update(Product product);

  Product delete(int id);

  Iterable<Product> findAll();

  Long getCollectionSize();

  Iterable<Product> getPage(int page);

  Page<Product> findPaginated(int page, int size, String sort);
}

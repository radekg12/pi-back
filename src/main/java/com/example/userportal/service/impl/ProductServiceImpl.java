package com.example.userportal.service.impl;

import com.example.userportal.domain.Product;
import com.example.userportal.repository.ProductRepository;
import com.example.userportal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  @Autowired
  public ProductServiceImpl(ProductRepository repository) {
    this.repository = repository;
  }

  public Product create(Product product) {
    return repository.save(product);
  }

  @Override
  public Product delete(int id) {
    Optional<Product> product = Optional.ofNullable(findById(id));
    product.ifPresent(repository::delete);
    return product.orElse(null);
  }

  @Override
  public Iterable<Product> findAll() {
    return repository.findAll();
  }

  @Override
  public Long getCollectionSize() {
    return repository.count();
  }

  @Override
  public Iterable<Product> getPage(int page) {
    return repository.findAll(PageRequest.of(page, 20, Sort.Direction.DESC, "id"));
  }

  @Override
  public Page<Product> findPaginated(int page, int size, String sort) {
    String[] sortBy = sort.split("_");
    String sortProperty = sortBy[0];
    Sort.Direction sortDirection = sortBy[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    return repository.findAll(PageRequest.of(page, size, sortDirection, sortProperty));
  }

  @Override
  public Product findById(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public Product update(Product product) {
    return null;
  }
}

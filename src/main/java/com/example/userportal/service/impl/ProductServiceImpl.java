package com.example.userportal.service.impl;

import com.example.userportal.domain.Product;
import com.example.userportal.repository.ProductRepository;
import com.example.userportal.service.ProductService;
import com.example.userportal.service.dto.ProductDTO;
import com.example.userportal.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;
  private final ProductMapper mapper;

  @Autowired
  public ProductServiceImpl(ProductRepository repository, ProductMapper productMapper) {
    this.repository = repository;
    this.mapper = productMapper;
  }

  public ProductDTO create(ProductDTO productDto) {
    Product product = mapper.toProduct(productDto);
    return mapper.toProductDto(repository.save(product));
  }

  @Override
  public ProductDTO delete(int id) {
    Optional<Product> product = repository.findById(id);
    product.ifPresent(repository::delete);
    return mapper.toProductDto(product.orElse(null));
  }

  @Override
  public Iterable<ProductDTO> findAll() {
    Iterable<Product> products = repository.findAll();
    return mapper.toProductDtos(products);
  }

  @Override
  public Long getCollectionSize() {
    return repository.count();
  }

//  @Override
//  public Iterable<ProductDTO> getPage(int page) {
//    return repository.findAll(PageRequest.of(page, 20, Sort.Direction.DESC, "id"));
//  }

  @Override
  public Page<ProductDTO> findPaginated(int page, int size, String sort) {
    String[] sortBy = sort.split("_");
    String sortProperty = sortBy[0];
    Sort.Direction sortDirection = sortBy[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Page<Product> productPage = repository.findAll(PageRequest.of(page, size, sortDirection, sortProperty));
    return mapper.toPageOfProductDtos(productPage);
  }

  @Override
  public Page<ProductDTO> findSubcategoryPaginated(int subcategoryId, int page, int size, String sort) {
    String[] sortBy = sort.split("_");
    String sortProperty = sortBy[0];
    Sort.Direction sortDirection = sortBy[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Page<Product> productPage = repository.findProductsByProductSubcategoryId(subcategoryId, PageRequest.of(page, size, sortDirection, sortProperty));
    return mapper.toPageOfProductDtos(productPage);
  }

  @Override
  public ProductDTO findById(int id) {
    Product product = repository.findById(id).orElse(null);
    return mapper.toProductDto(product);
  }

  @Override
  public ProductDTO update(ProductDTO productDto) {
    return null;
  }
}

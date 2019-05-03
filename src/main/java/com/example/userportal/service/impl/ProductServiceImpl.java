package com.example.userportal.service.impl;

import com.example.userportal.domain.Product;
import com.example.userportal.domain.SpecificationPosition;
import com.example.userportal.exception.InternalServerErrorException;
import com.example.userportal.repository.ProductRepository;
import com.example.userportal.repository.SpecificationPositionRepository;
import com.example.userportal.service.ProductService;
import com.example.userportal.service.dto.ProductDTO;
import com.example.userportal.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;
  private final SpecificationPositionRepository specificationPositionRepository;
  private final ProductMapper mapper;


  @Override
  @Transactional
  public ProductDTO create(ProductDTO productDto) {
    Product product = mapper.toProduct(productDto);
    Collection<SpecificationPosition> specificationPositions = product.getSpecificationPositions();
    product.setSpecificationPositions(null);
    repository.save(product);
    specificationPositions
            .forEach(position -> {
              position.setProductByProductId(product);
              specificationPositionRepository.save(position);
            });

    product.setSpecificationPositions(specificationPositions);

    return mapper.toProductDto(product);
  }

  @Override
  public ProductDTO delete(int id) {
    Optional<Product> product = repository.findById(id);
    product.ifPresent(repository::delete);
    return mapper.toProductDto(product
            .orElseThrow(() -> new InternalServerErrorException("Order id=" + id + " could not be found")));
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


  @Override
  public Page<ProductDTO> findPaginated(int page, int size, String sort) {
    String[] sortBy = sort.split("_");
    String sortProperty = sortBy[0];
    Sort.Direction sortDirection = sortBy[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Page<Product> productPage = repository.findAll(PageRequest.of(page, size, sortDirection, sortProperty));
    return mapper.toPageOfProductDtos(productPage);
  }

  @Override
  public Iterable<ProductDTO> findAllProductsBySubcategory(int subcategoryId) {
    Iterable<Product> products = repository.findAllByProductSubcategoryId(subcategoryId);
    return mapper.toProductDtos(products);
  }

  @Override
  public Iterable<ProductDTO> findAllProductsByCategory(int categoryId) {
    Iterable<Product> products = repository.findAllByProductCategoryId(categoryId);
    return mapper.toProductDtos(products);
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
    Product product = repository.findById(id).orElseThrow(() -> new InternalServerErrorException("Product id=" + id + " could not be found"));
    return mapper.toProductDto(product);
  }

  @Override
  @Transactional
  public ProductDTO update(ProductDTO productDto) {

    Product product = mapper.toProduct(productDto);

    List<Integer> newSpecificationPositionIds = product.getSpecificationPositions()
            .stream()
            .map(SpecificationPosition::getId)
            .collect(Collectors.toList());

    List<SpecificationPosition> oldSpecificationPositions = StreamSupport.stream(specificationPositionRepository.findAllByProductId(product.getId()).spliterator(), false)
            .collect(Collectors.toList());

    oldSpecificationPositions
            .stream()
            .filter(oldPosition -> !newSpecificationPositionIds.contains(oldPosition.getId()))
            .forEach(specificationPositionRepository::delete);

    product.getSpecificationPositions()
            .forEach(position -> position.setProductByProductId(product));

    Product savedProduct = repository.save(product);
    return mapper.toProductDto(savedProduct);
  }

  @Override
  public ProductDTO updatePhysicalQuantity(int productId, int quantity) {
    Optional<Product> product = repository.findById(productId);
    product.ifPresent(p -> {
      Integer currentPhysicalQuantity = p.getPhysicalQuantityInStock();
      p.setPhysicalQuantityInStock(currentPhysicalQuantity + quantity);
      repository.save(p);
    });
    return mapper.toProductDto(product.orElseThrow(() -> new InternalServerErrorException("Product id=" + productId + " could not be found")));
  }
}

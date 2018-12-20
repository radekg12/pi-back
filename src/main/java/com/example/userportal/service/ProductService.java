package com.example.userportal.service;

import com.example.userportal.service.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
  ProductDTO create(ProductDTO productDto);

  Page<ProductDTO> findSubcategoryPaginated(int subcategoryId, int page, int size, String sort);

  ProductDTO findById(int id);

  ProductDTO delete(int id);

  Iterable<ProductDTO> findAll();

  Long getCollectionSize();

//  Iterable<Product> getPage(int page);

  Page<ProductDTO> findPaginated(int page, int size, String sort);

  ProductDTO update(ProductDTO productDto);
}

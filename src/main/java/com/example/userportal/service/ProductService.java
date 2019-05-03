package com.example.userportal.service;

import com.example.userportal.service.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
  ProductDTO create(ProductDTO productDto);

  Iterable<ProductDTO> findAllProductsBySubcategory(int subcategoryId);

  Iterable<ProductDTO> findAllProductsByCategory(int categoryId);

  Page<ProductDTO> findSubcategoryPaginated(int subcategoryId, int page, int size, String sort);

  ProductDTO findById(int id);

  ProductDTO delete(int id);

  Iterable<ProductDTO> findAll();

  Long getCollectionSize();

  Page<ProductDTO> findPaginated(int page, int size, String sort);

  ProductDTO update(ProductDTO productDto);

  ProductDTO updatePhysicalQuantity(int productId, int quantity);
}

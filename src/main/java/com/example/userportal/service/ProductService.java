package com.example.userportal.service;

import com.example.userportal.service.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.RedisConnectionFailureException;

import java.util.List;

public interface ProductService {
  ProductDTO create(ProductDTO productDto);

  Page<ProductDTO> findPaginated(Pageable pageRequest);

  Page<ProductDTO> findCategoryPaginated(int categoryId, Pageable pageRequest);

  Page<ProductDTO> findSubcategoryPaginated(int subcategoryId, Pageable pageRequest);

  Iterable<ProductDTO> findAllProductsBySubcategory(int subcategoryId);

  Iterable<ProductDTO> findAllProductsByCategory(int categoryId);

  ProductDTO findById(int id);

  ProductDTO delete(int id);

  Iterable<ProductDTO> findAll();

  Long getCollectionSize();

  ProductDTO update(ProductDTO productDto);

  ProductDTO updatePhysicalQuantity(int productId, int quantity);

  List<ProductDTO> getProductRecommendation(int productId) throws RedisConnectionFailureException;
}

package com.example.userportal.repository;

import com.example.userportal.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

  @Query("SELECT p FROM Product p WHERE p.productSubcategory.id=:subcategoryId")
  Page<Product> findProductsByProductSubcategoryId(@Param("subcategoryId") int subcategoryId, Pageable pageable);

}

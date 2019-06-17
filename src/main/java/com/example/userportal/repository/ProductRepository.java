package com.example.userportal.repository;

import com.example.userportal.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

  @Query("SELECT p FROM Product p WHERE p.productSubcategory.id=:subcategoryId")
  Page<Product> findProductsByProductSubcategoryId(@Param("subcategoryId") int subcategoryId, Pageable pageable);

  @Query("SELECT p FROM Product p WHERE p.productSubcategory.productCategory.id=:categoryId")
  Page<Product> findProductsByProductCategoryId(@Param("categoryId") int categoryId, Pageable pageable);

  @Modifying
  @Query("UPDATE Product p SET p.logicalQuantityInStock = p.logicalQuantityInStock - :quantity WHERE p.id = :productId")
  Integer sellProducts(@Param("productId") int productId,
                       @Param("quantity") int quantity);

  @Query("SELECT p FROM Product p WHERE p.productSubcategory.id=:subcategoryId")
  Iterable<Product> findAllByProductSubcategoryId(@Param("subcategoryId") int subcategoryId);

  @Query("SELECT p FROM Product p WHERE p.productSubcategory.productCategory.id=:categoryId")
  Iterable<Product> findAllByProductCategoryId(@Param("categoryId") int categoryId);
}

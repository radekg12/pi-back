package com.example.userportal.repository;

import com.example.userportal.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<ProductCategory, Integer> {

  @Query("SELECT s.productCategory FROM ProductSubcategory s WHERE s.id=:subcategoryId")
  ProductCategory findProductCategoryByProductSubcategoryId(@Param("subcategoryId") int subcategoryId);
}

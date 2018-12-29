package com.example.userportal.repository;

import com.example.userportal.domain.ProductCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends CrudRepository<ProductCategory, Integer> {

  @Query("SELECT s.productCategory FROM ProductSubcategory s WHERE s.productCategory.id=:subcategoryId")
  ProductCategory findProductCategoryByProductSubcategoryId(@Param("subcategoryId") int subcategoryId);
}

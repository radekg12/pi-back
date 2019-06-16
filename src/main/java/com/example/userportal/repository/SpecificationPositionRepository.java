package com.example.userportal.repository;

import com.example.userportal.domain.SpecificationPosition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SpecificationPositionRepository extends CrudRepository<SpecificationPosition, Integer> {

  @Query("SELECT p FROM SpecificationPosition p WHERE p.product.id=:productId")
  Iterable<SpecificationPosition> findAllByProductId(@Param("productId") int productId);
}

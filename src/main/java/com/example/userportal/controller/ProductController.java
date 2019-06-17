package com.example.userportal.controller;

import com.example.userportal.service.ProductService;
import com.example.userportal.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/products"})
public class ProductController {
  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(path = "/all")
  public List<ProductDTO> findAll() {
    return productService.findAll();
  }

  @GetMapping
  public Page<ProductDTO> findAllByPage(Pageable pageRequest) {
    return productService.findPaginated(pageRequest);
  }

  @GetMapping(path = {"/categories/{categoryId}"})
  public Page<ProductDTO> findAllByCategoryPage(@PathVariable("categoryId") int categoryId, Pageable pageRequest) {
    return productService.findCategoryPaginated(categoryId, pageRequest);
  }

  @GetMapping(path = {"/subcategories/{subcategoryId}"})
  public Page<ProductDTO> findAllBySubcategoryPage(@PathVariable("subcategoryId") int subcategoryId, Pageable pageRequest) {
    return productService.findSubcategoryPaginated(subcategoryId, pageRequest);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER')")
  @PostMapping
  public ProductDTO createNewProduct(@Valid @RequestBody ProductDTO productDto) {
    return productService.create(productDto);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER')")
  @PutMapping
  public ProductDTO updateProduct(@Valid @RequestBody ProductDTO productDto) {
    return productService.update(productDto);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER')")
  @DeleteMapping(path = "/{id}")
  public ProductDTO deleteProduct(@PathVariable("id") int id) {
    return productService.delete(id);
  }
}

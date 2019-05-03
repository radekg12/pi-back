package com.example.userportal.controller;

import com.example.userportal.service.ProductService;
import com.example.userportal.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.RedisConnectionFailureException;
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


  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ProductDTO create(@Valid @RequestBody ProductDTO productDto) {
    return productService.create(productDto);
  }

  @GetMapping(path = {"/detail/{id}"})
  public ProductDTO findOne(
          @PathVariable("id") int id) {
    return productService.findById(id);
  }

  @GetMapping(path = "all")
  public Iterable<ProductDTO> findAll() {
    return productService.findAll();
  }

  @GetMapping
  public Page<ProductDTO> findAllByPage(Pageable pageRequest) {
    return productService.findPaginated(pageRequest);
  }

  @GetMapping(path = {"/category/{category}"})
  public Page<ProductDTO> findAllByCategoryPage(@PathVariable("category") int categoryId, Pageable pageRequest) {
    return productService.findCategoryPaginated(categoryId, pageRequest);
  }

  @GetMapping(path = {"/{subcategory}"})
  public Page<ProductDTO> findAllBySubcategoryPage(@PathVariable("subcategory") int subcategoryId, Pageable pageRequest) {
    return productService.findSubcategoryPaginated(subcategoryId, pageRequest);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping({"/warehouse/take/{id}"})
  public ProductDTO takeProductFromWarehouse(@PathVariable("id") int id,
                                             @RequestParam(value = "quantity") int quantity) {
    return productService.updatePhysicalQuantity(id, quantity);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping({"/warehouse/put/{id}"})
  public ProductDTO putProductToWarehouse(@PathVariable("id") int id,
                                          @RequestParam(value = "quantity") int quantity) {
    return productService.updatePhysicalQuantity(id, quantity * (-1));
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  public ProductDTO update(
          @Valid @RequestBody ProductDTO productDto) {
    return productService.update(productDto);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping(path = "/{id}")
  public ProductDTO delete(
          @PathVariable("id") int id) {
    return productService.delete(id);
  }

  @GetMapping(path = "/{id}/recommendation")
  public List<ProductDTO> getRecommendation(@PathVariable("id") int productId) {
    List<ProductDTO> products;
    try {
      products = productService.getProductRecommendation(productId);
    } catch (RedisConnectionFailureException e) {
      products = productService.findPaginated(PageRequest.of(0, 8, Sort.Direction.ASC, "id")).getContent();
    }
    return products;
  }
}

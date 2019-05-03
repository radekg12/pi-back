package com.example.userportal.controller;

import com.example.userportal.service.ProductService;
import com.example.userportal.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
  public Page<ProductDTO> findAllByPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                        @RequestParam(value = "per_page", required = false, defaultValue = "2") int size,
                                        @RequestParam(value = "sort_by", required = false, defaultValue = "unitPrice_asc") String sort) {
    return productService.findPaginated(page, size, sort);
  }

  @GetMapping(path = {"/{subcategory}"})
  public Page<ProductDTO> findAllByPage2(@PathVariable("subcategory") int subcategoryId,
                                         @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                         @RequestParam(value = "per_page", required = false, defaultValue = "2") int size,
                                         @RequestParam(value = "sort_by", required = false, defaultValue = "unitPrice_asc") String sort) {
    return productService.findSubcategoryPaginated(subcategoryId, page, size, sort);
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


}

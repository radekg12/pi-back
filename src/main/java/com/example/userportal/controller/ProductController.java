package com.example.userportal.controller;

import com.example.userportal.service.ProductService;
import com.example.userportal.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/products"})
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ProductDTO create(@RequestBody ProductDTO productDto) {
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

  @PutMapping
  public ProductDTO update(
          @RequestBody ProductDTO productDto) {
    return productService.update(productDto);
  }

  @DeleteMapping(path = "/{id}")
  public ProductDTO delete(
          @PathVariable("id") int id) {
    return productService.delete(id);
  }


}

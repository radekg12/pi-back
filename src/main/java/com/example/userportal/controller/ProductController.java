package com.example.userportal.controller;

import com.example.userportal.domain.Product;
import com.example.userportal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.55:4200", "http://192.168.0.55:8081", "http://localhost:8081"}, maxAge = 3600)
@RestController
//@RequestMapping({"/api"})
@RequestMapping({"/products"})
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public Product create(@RequestBody Product product) {
    return productService.create(product);
  }

  @GetMapping(path = {"/{id}"})
  public Product findOne(@PathVariable("id") int id) {
    return productService.findById(id);
  }

  @GetMapping
  public Page<Product> findAllByPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                     @RequestParam(value = "per_page", required = false, defaultValue = "2") int size,
                                     @RequestParam(value = "sort_by", required = false, defaultValue = "id_asc") String sort) {
    return productService.findPaginated(page, size, sort);
  }

  @PutMapping
  public Product update(@RequestBody Product product) {
    return productService.update(product);
  }

  @DeleteMapping(path = "/{id}")
  public Product delete(@PathVariable("id") int id) {
    return productService.delete(id);
  }

//  @GetMapping
//  public Iterable<User> findAll() {
//    return productService.findAll();
//  }

}

package com.example.userportal.controller;

import com.example.userportal.service.ProductService;
import com.example.userportal.service.dto.ProductDTO;
import com.example.userportal.service.impl.RecommendationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/products"})
public class ProductController {

  private final ProductService productService;
  private final RecommendationServiceImpl recommendationService;

  @Autowired
  public ProductController(ProductService productService, RecommendationServiceImpl recommendationService) {
    this.productService = productService;
    this.recommendationService = recommendationService;
  }


  @PreAuthorize("hasRole('ROLE_ADMIN')")
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

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  public ProductDTO update(
          @RequestBody ProductDTO productDto) {
    return productService.update(productDto);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping(path = "/{id}")
  public ProductDTO delete(
          @PathVariable("id") int id) {
    return productService.delete(id);
  }

  @GetMapping(path = "/{id}/recommendation")
  public List<ProductDTO> getRecommendation(@PathVariable("id") int id) {
     ProductDTO product = productService.findById(id);
     List<ProductDTO> productDTOList = new ArrayList<>();
     productDTOList.add(product);
     Set<String> recommendations =  this.recommendationService.getRecommendation(productDTOList, 6);
    return recommendations
            .stream()
            .mapToInt(Integer::parseInt)
            .mapToObj(productService::findById)
            .collect(Collectors.toList());
  }
}

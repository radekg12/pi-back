package com.example.userportal.controller;

import com.example.userportal.service.ProductService;
import com.example.userportal.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}")
public class ProductDetailsController {
  private final ProductService productService;

  @Autowired
  public ProductDetailsController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(path = {"/details"})
  public ProductDTO getProduct(@PathVariable("productId") int productId) {
    return productService.findById(productId);
  }

  @GetMapping(path = "/recommendations")
  public List<ProductDTO> getRecommendation(@PathVariable("productId") int productId) {
    return productService.getProductRecommendation(productId);
  }
}

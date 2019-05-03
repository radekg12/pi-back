package com.example.userportal.service;

import com.example.userportal.service.dto.ProductDTO;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public interface RecommendationService {

  void addProductsRating(Collection<ProductDTO> products);

  Set<String> getRecommendation(Collection<ProductDTO> products, int maxResults) throws RedisConnectionFailureException;
}

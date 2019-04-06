package com.example.userportal.service.impl;

import com.example.userportal.domain.Product;
import com.example.userportal.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class RecommendationServiceImpl {
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RecommendationServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String getProductKey(int id) {
        return String.format("product:%d:purchased_with", id);
    }

    public void addProductsRating(Collection<Product> products) {
        for (Product product : products) {
            for (Product withProduct : products) {
                if (!withProduct.equals(product)) {
                    redisTemplate.boundZSetOps(this.getProductKey(product.getId()))
                            .incrementScore(Integer.toString(withProduct.getId()), 1);
                }
            }
        }
    }

    public Set<String> getRecommendation(Collection<ProductDTO> products, int maxResults) {
        if (products.size() == 1) {
            ProductDTO head = products.iterator().next();
            return redisTemplate.boundZSetOps(this.getProductKey(head.getId())).reverseRange(0, maxResults);
        } else {
            // Create temporary key to combine scores of all related product
            StringBuilder tmp = new StringBuilder();
            products.forEach(product -> tmp.append(product.getId()));
            String tmp_key = tmp.toString();
            Set<String> productsIds = new HashSet<>();
            int headId = products.iterator().next().getId();
            products.forEach(product -> productsIds.add(Integer.toString(product.getId())));
            productsIds.remove(Integer.toString(headId));
            redisTemplate.opsForZSet().unionAndStore(getProductKey(headId), productsIds, tmp_key);
            redisTemplate.boundZSetOps(tmp_key).remove(productsIds.toArray(new String[productsIds.size()]));
            Set<String> recommendations = redisTemplate.boundZSetOps(tmp_key).reverseRange(0, maxResults);
            redisTemplate.delete(tmp_key);
            return recommendations;
        }
    }
}
